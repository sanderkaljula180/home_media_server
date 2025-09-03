INSERT INTO movie_videos (Id, filename, format, duration, size, path, resolution)
VALUES (1, 'the_batman.mp4', 'mp4', '02:56:12', 8448098304, 'movies/the_batman/the_batman.mp4', '3840x1606');

INSERT INTO movies (id, movie_name, description, rating, thumbnail_url, release_date, movie_video_id)
VALUES (1, 'The Batman', 'When a sadistic serial killer begins murdering key political figures in Gotham, the Batman is forced to investigate the citys hidden corruption and question his familys involvement.', 8.7,
        '/images/the_batman.jpg', '2022-03-04', 1);

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
