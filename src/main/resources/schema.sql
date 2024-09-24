CREATE TABLE IF NOT EXISTS ITEMS
(
    id    VARCHAR(64) DEFAULT RANDOM_UUID() PRIMARY KEY,
    name  VARCHAR(64) NOT NULL,
    price NUMERIC(16, 2) NOT NULL,
    type VARCHAR(64) NOT NULL
);