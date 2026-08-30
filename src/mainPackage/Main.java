package mainPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dataBase.DataBase;
import io.github.cdimascio.dotenv.Dotenv;
import media.MediaRepository;
import order.OrderRepository;
import order.item.ItemRepository;
import product.GameRepository;
import profile.ProfileRepository;

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
		Dotenv dotenv = Dotenv.load();
		String dbUrl = dotenv.get("DB_URL");
		String dbUsername = dotenv.get("DB_USER");
		String dbPassword = dotenv.get("DB_PASSWORD");
		DataBase dataBase = new DataBase(dbUrl, dbUsername, dbPassword);
		
		ProfileRepository.dataBase = dataBase;
		OrderRepository.dataBase = dataBase;
		ItemRepository.dataBase = dataBase;
		GameRepository.dataBase = dataBase;
		MediaRepository.dataBase = dataBase;
		
		// import uuid inside postgresql
		dataBase.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";");
		
		// create tables
		dataBase.execute("CREATE TABLE IF NOT EXISTS games ( id UUID NOT NULL PRIMARY KEY, title VARCHAR(50) NOT NULL, cover_image VARCHAR(100) NOT NULL, list_price INT NOT NULL, sale_price INT NOT NULL, is_active BOOLEAN NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS users ( id UUID NOT NULL PRIMARY KEY, first_name VARCHAR(10) NOT NULL, last_name VARCHAR(10) NOT NULL, wallet INT NOT NULL, points INT NOT NULL, email VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, creation_date TIMESTAMPTZ NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS medias ( id UUID NOT NULL PRIMARY KEY, url VARCHAR(200) NOT NULL, type VARCHAR(5) NOT NULL, alt VARCHAR(50) NOT NULL );");
		// TODO: create payment table
		dataBase.execute("CREATE TABLE IF NOT EXISTS orders ( id UUID NOT NULL PRIMARY KEY, user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE, total INT, discount INT, placed_order BOOL NOT NULL, creation_date DATE NOT NULL, payment UUID NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS order_item ( id SERIAL PRIMARY KEY, game_id UUID NOT NULL REFERENCES games(id) ON DELETE CASCADE, total INT, order_id UUID NOT NULL REFERENCES orders(id) ON DELETE CASCADE );");
		
		SpringApplication.run(Main.class, args);
		
		//dataBase.disconnect();
	}
}
