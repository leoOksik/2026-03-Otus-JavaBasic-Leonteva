INSERT INTO chat.users (name, email, password)
VALUES ('admin',
        'admin@chat.ru',
        '$2a$12$.aujIg2W3xd.2RSYeB23.uf2Kq2DaG8CXOZooYIYP/ht2RoE/OXHC')
    ON CONFLICT (email) DO NOTHING;

INSERT INTO chat.users_roles (user_id, role_id)
SELECT u.id, r.id
FROM chat.users u,
     chat.roles r
WHERE u.email = 'admin@chat.ru'
  AND r.name = 'ADMIN' ON CONFLICT (user_id) DO NOTHING;
