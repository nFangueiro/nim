Drop table game;

CREATE TABLE IF NOT EXISTS Game
(
    id              VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    num_matches     SMALLINT NOT NULL,
    max_take        SMALLINT NOT NULL,
    timestamp       TIMESTAMP
);