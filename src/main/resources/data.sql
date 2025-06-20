INSERT INTO videos (id, name, duration, description)
VALUES (1, 'toystory1.mp4', 5420, 'Epic action flick');

INSERT INTO videos (id, name, duration, description)
VALUES (2, 'toystory2.mp4', 6340, 'Cozy indie drama');

INSERT INTO roles (role_name)
VALUES ('ADMIN');

INSERT INTO roles (role_name)
VALUES ('DEFAULT');

INSERT INTO users (username, password)
VALUES ('admin_user', 'password');

INSERT INTO users_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE username = 'admin_user'),
    (SELECT id FROM roles WHERE role_name = 'ADMIN')
);