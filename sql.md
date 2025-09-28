CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE games {
	game_id UUID NOT NULL PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	cover_image VARCHAR(100) NOT NULL,
	list_price INT NOT NULL,
	sale_price INT NOT NULL,
	is_active BOOLEAN NOT NULL
};

CREATE TABLE users {
	user_id UUID NOT NULL PRIMARY KEY,
	first_name VARCHAR(10) NOT NULL,
	last_name VARCHAR(10) NOT NULL,
	wallet INT NOT NULL,
	points INT NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	creation_date DATE NOT NULL
};

CREATE TABLE user_games {
	user_id UUID REFERENCES users(user_id),
	game_id UUID REFERENCES games(game_id),
	PRIMARY KEY (user_id, game_id)
};