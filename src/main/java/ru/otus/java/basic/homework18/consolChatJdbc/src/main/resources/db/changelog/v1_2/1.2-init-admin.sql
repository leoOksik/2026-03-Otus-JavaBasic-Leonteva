INSERT INTO chat.users (name, email, password)
VALUES ('admin', 'admin@chat.ru', 'admin123') ON CONFLICT DO NOTHING;

INSERT INTO chat.users_roles (user_id, role_id)
SELECT u.id, r.id
FROM chat.users u, chat.roles r
WHERE u.email = 'admin@chat.ru' AND r.name = 'ADMIN' ON CONFLICT DO NOTHING;
