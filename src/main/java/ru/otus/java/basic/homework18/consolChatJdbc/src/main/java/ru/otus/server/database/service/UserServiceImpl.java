package ru.otus.server.database.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.postgresql.util.PSQLState;
import ru.otus.server.database.DbConnection;
import ru.otus.exception.UserAlreadyExistsException;
import ru.otus.exception.UserNotFoundException;
import ru.otus.exception.WrongPasswordException;
import ru.otus.server.database.entity.User;
import ru.otus.server.database.entity.UserRole;
import ru.otus.server.database.security.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Slf4j
public class UserServiceImpl implements UserService {


    private static final String INSERT_USER_SQL = """
        INSERT INTO chat.users (name, email, password)
        VALUES (?, ?, ?)
        RETURNING id
        """;

    private static final String INSERT_USER_ROLE_SQL = """
        INSERT INTO chat.users_roles (user_id, role_id)
        SELECT ?, r.id FROM chat.roles r WHERE r.name = ?
        """;

    private static final String FIND_BY_EMAIL_SQL = """
        SELECT u.name, u.password, r.name AS role
        FROM chat.users u
        LEFT JOIN chat.users_roles ur ON ur.user_id = u.id
        LEFT JOIN chat.roles r        ON r.id = ur.role_id
        WHERE u.email = ?
        """;

    private final DbConnection db;

    private final PasswordHasher passwordHasher;

    public UserServiceImpl(DbConnection db, PasswordHasher passwordHasher) {
        this.db = db;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public User login(String email, String password) {
        if (StringUtils.isAnyBlank(email, password)) {
            throw new IllegalArgumentException("email or password is blank");
        }
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_EMAIL_SQL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new UserNotFoundException("User not found");
                }

                String storedHash = rs.getString("password");
                if (!passwordHasher.matches(password, storedHash)) {
                    throw new WrongPasswordException("wrong password");
                }

                return User.builder()
                        .name(rs.getString("name"))
                        .login(email)
                        .role(UserRole.valueOf(rs.getString("role")))
                        .build();
            }
        } catch (SQLException ex) {
            log.error("Failed login {}", email, ex);
            throw new IllegalStateException("Error login", ex);
        }
    }

    @Override
    public User register(String name, String email, String password) {
        if (StringUtils.isAnyBlank(name, email, password)) {
            throw new IllegalArgumentException("Name, email or password is blank");
        }
        try (Connection conn = db.getConnection()) {
            conn.setAutoCommit(false);

            String encoded = passwordHasher.encode(password);
            UUID userId = save(conn, name, email, encoded);

            if (userId == null) {
                throw new UserAlreadyExistsException("Name or email already exists");
            }
            addRole(conn, userId);
            conn.commit();
            return User.builder()
                    .name(name)
                    .login(email)
                    .role(UserRole.USER)
                    .build();
        } catch (SQLException ex) {
            log.error("Failed register {}", email, ex);
            throw new IllegalStateException("Error register", ex);
        }
    }

    private UUID save(Connection conn, String name, String email, String hashPassword) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hashPassword);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getObject("id", UUID.class) : null;
            }
        } catch (SQLException e) {
            if (PSQLState.UNIQUE_VIOLATION.getState().equals(e.getSQLState())) {
                return null;
            }
            throw new IllegalStateException("Save user error", e);
        }
    }

    private void addRole(Connection conn, UUID userId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_USER_ROLE_SQL)) {
            ps.setObject(1, userId);
            ps.setString(2, UserRole.USER.name());
            if (ps.executeUpdate() == 0) {
                throw new IllegalStateException("role USER not found");
            }
        }
    }
}
