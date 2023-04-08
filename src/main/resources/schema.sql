CREATE TABLE IF NOT EXISTS users
(
    uuid UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO users (uuid, name) VALUES (RANDOM_UUID(), 'Admin');
INSERT INTO users (uuid, name) VALUES (RANDOM_UUID(), 'User');