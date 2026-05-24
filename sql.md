# Data base init SQL
``` sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE games (
	id UUID NOT NULL PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	cover_image VARCHAR(100) NOT NULL,
	list_price INT NOT NULL,
	sale_price INT NOT NULL,
	is_active BOOLEAN NOT NULL
);

CREATE TABLE users (
	id UUID NOT NULL PRIMARY KEY,
	first_name VARCHAR(10) NOT NULL,
	last_name VARCHAR(10) NOT NULL,
	wallet INT NOT NULL,
	points INT NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	creation_date DATE NOT NULL
);

CREATE TABLE user_games (
	user_id UUID NOT NULL REFERENCES users(id),
	game_id UUID NOT NULL REFERENCES games(id),
	PRIMARY KEY (user_id, game_id)
);

CREATE TABLE medias (
	id UUID NOT NULL PRIMARY KEY,
	url VARCHAR(200) NOT NULL,
	type VARCHAR(5) NOT NULL,
	alt VARCHAR(50) NOT NULL
);

CREATE TABLE user_medias (
	user_id UUID NOT NULL REFERENCES users(id),
	media_id UUID NOT NULL REFERENCES medias(id),
	PRIMARY KEY (user_id, media_id)
);

CREATE TABLE order (
	id UUID NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	price INT NOT NULL
);

CREATE TABLE address (
	id UUID NOT NULL PRIMARY KEY,
	address_1 VARCHAR(50) NOT NULL,
	address_2 VARCHAR(50),
	postal_code INT NOT NULL,
	full_name VARCHAR(100) NOT NULL,
	observation VARCHAR(200),
	city VARCHAR(50) NOT NULL,
	state VARCHAR(50) NOT NULL
);
```