
public class Main {
	public static void main(String[] args) {
		String dbUrl = "jdbc:postgresql://localhost:5432/online-game-store-db";
		String dbUsername = "admin";
		String dbPassword = "admin";
		DataBase dataBase = new DataBase(dbUrl, dbUsername, dbPassword);
		
		dataBase.execute("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(50), email VARCHAR(50), password VARCHAR(20))");
		
		dataBase.disconnect();
	}
}
