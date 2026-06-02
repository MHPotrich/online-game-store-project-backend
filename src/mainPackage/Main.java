package mainPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dataBase.DataBase;
import media.MediaController;
import order.OrderController;
import product.GameController;
import profile.ProfileController;

@SpringBootApplication(scanBasePackages = {
		"mainPackage",
		"media",
		"order",
		"payment",
		"product",
		"profile"
})
public class Main {
	public static void main(String[] args) {
		String dbUrl = "jdbc:postgresql://localhost:5432/online-game-store-db";
		String dbUsername = "admin";
		String dbPassword = "admin";
		DataBase dataBase = new DataBase(dbUrl, dbUsername, dbPassword);
		
		GameController.dataBase = dataBase;
		ProfileController.dataBase = dataBase;
		MediaController.dataBase = dataBase;
		OrderController.dataBase = dataBase;
		
		// import uuid inside postgresql
		dataBase.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";");
		
		// create tables
		dataBase.execute("CREATE TABLE IF NOT EXISTS games ( id UUID NOT NULL PRIMARY KEY, title VARCHAR(50) NOT NULL, cover_image VARCHAR(100) NOT NULL, list_price INT NOT NULL, sale_price INT NOT NULL, is_active BOOLEAN NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS users ( id UUID NOT NULL PRIMARY KEY, first_name VARCHAR(10) NOT NULL, last_name VARCHAR(10) NOT NULL, wallet INT NOT NULL, points INT NOT NULL, email VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, creation_date DATE NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS medias ( id UUID NOT NULL PRIMARY KEY, url VARCHAR(200) NOT NULL, type VARCHAR(5) NOT NULL, alt VARCHAR(50) NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS orders ( id SERIAL PRIMARY KEY, profile UUID NOT NULL, total INT, discount INT, placed_order BOOL NOT NULL, creation_date DATE NOT NULL, payment UUID NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS order_item ( id SERIAL PRIMARY KEY, game UUID NOT NULL, total INT );");
		
		// create relation tables
		dataBase.execute("CREATE TABLE IF NOT EXISTS user_games ( user_id UUID NOT NULL REFERENCES users(id), game_id UUID NOT NULL REFERENCES games(id), PRIMARY KEY (user_id, game_id) );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS user_medias ( user_id UUID NOT NULL REFERENCES users(id), media_id UUID NOT NULL REFERENCES medias(id), PRIMARY KEY (user_id, media_id) );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS order_items ( order_id INT NOT NULL REFERENCES orders(id), item_id INT NOT NULL REFERENCES order_item(id), PRIMARY KEY (order_id, item_id) );");
		
		SpringApplication.run(Main.class, args);
		
		//dataBase.disconnect();
	}
}
