CREATE TABLE IF NOT EXISTS ITEMS
(
    id    VARCHAR(32) DEFAULT RANDOM_UUID() PRIMARY KEY,
    name  VARCHAR(60) NOT NULL,
    price NUMERIC(20, 2)
);