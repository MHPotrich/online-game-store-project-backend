package mainPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		String dbUrl = "jdbc:postgresql://localhost:5432/online-game-store-db";
		String dbUsername = "admin";
		String dbPassword = "admin";
		DataBase dataBase = new DataBase(dbUrl, dbUsername, dbPassword);
		
		GameController.dataBase = dataBase;
		UserController.dataBase = dataBase;
		
		// import uuid inside postgresql
		dataBase.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";");
		
		// create tables
		dataBase.execute("CREATE TABLE IF NOT EXISTS games ( id UUID NOT NULL PRIMARY KEY, title VARCHAR(50) NOT NULL, cover_image VARCHAR(100) NOT NULL, list_price INT NOT NULL, sale_price INT NOT NULL, is_active BOOLEAN NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS users ( id UUID NOT NULL PRIMARY KEY, first_name VARCHAR(10) NOT NULL, last_name VARCHAR(10) NOT NULL, wallet INT NOT NULL, points INT NOT NULL, email VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, creation_date DATE NOT NULL );");
		dataBase.execute("CREATE TABLE IF NOT EXISTS user_games ( user_id UUID NOT NULL REFERENCES users(id), game_id UUID NOT NULL REFERENCES games(id), PRIMARY KEY (user_id, game_id) );");
		
		SpringApplication.run(Main.class, args);
		
		//dataBase.disconnect();
	}
}
