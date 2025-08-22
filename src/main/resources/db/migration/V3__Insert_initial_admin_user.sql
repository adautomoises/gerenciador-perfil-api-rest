INSERT INTO users (login, url) VALUES ('adautomoises', 'https://api.github.com/users/adautomoises');

INSERT INTO auth_user (login, password, user_id) VALUES (
    'admin',
    '$2a$10$7Bx45KuYtY1jJ5iAN/smTOUJv7ovCpzojPRSSY4YJYI/z55FL/bH6',
    (SELECT id FROM users WHERE login = 'adautomoises')
);

INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);