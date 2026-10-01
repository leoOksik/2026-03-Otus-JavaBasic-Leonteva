CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.users
(
    id       UUID PRIMARY KEY DEFAULT uuidv7(),
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.roles
(
    id   UUID PRIMARY KEY DEFAULT uuidv7(),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.users_roles
(
    id      UUID PRIMARY KEY DEFAULT uuidv7(),
    user_id UUID NOT NULL,
    role_id UUID NOT NULL
);

ALTER TABLE chat.users ADD CONSTRAINT uq_users_name UNIQUE (name);

ALTER TABLE chat.users ADD CONSTRAINT uq_users_email UNIQUE (email);

ALTER TABLE chat.roles ADD CONSTRAINT uq_roles_name UNIQUE (name);

ALTER TABLE chat.users_roles ADD CONSTRAINT uq_users_roles_user UNIQUE (user_id);

ALTER TABLE chat.users_roles
    ADD CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id) REFERENCES chat.users (id) ON DELETE CASCADE;

ALTER TABLE chat.users_roles
    ADD CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id) REFERENCES chat.roles (id) ON DELETE CASCADE;

CREATE INDEX idx_users_roles_role_id ON chat.users_roles (role_id);
