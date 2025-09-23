
public class Main {
	public static void main(String[] args) {
		String dbUrl = "jdbc:postgresql://localhost:5432/online-game-store-db";
		String dbUsername = "admin";
		String dbPassword = "admin";
		DataBase dataBase = new DataBase(dbUrl, dbUsername, dbPassword);
		
		dataBase.disconnect();
	}
}
