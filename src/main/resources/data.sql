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

INSERT INTO series (
    series_name,
    series_description,
    rating,
    thumbnail_url,
    release_date,
    seasons_number,
    episodes_number
) VALUES (
    'Breaking Bad',
    'A high school chemistry teacher turned methamphetamine manufacturer partners with a former student to secure his family''s financial future.',
    '9.5',
    'https://example.com/breaking-bad-thumbnail.jpg',
    '2008-01-20',
    5,
    62
);

INSERT INTO series (
    series_name,
    series_description,
    rating,
    thumbnail_url,
    release_date,
    seasons_number,
    episodes_number
) VALUES (
    'The Mandalorian',
    'A lone bounty hunter makes his way through the outer reaches of the galaxy, far from the authority of the New Republic, protecting a force-sensitive child.',
    '8.7',
    'https://example.com/mandalorian-thumbnail.jpg',
    '2019-11-12',
    3,
    24
);
