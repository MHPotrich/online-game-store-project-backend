import java.sql.*;

public class DataBase {
	private Connection connection;
	private Statement statement;
	
	DataBase() {
		String url = "";
		String username = "";
		String password = "";
		
		try {
			this.connection = DriverManager.getConnection(url, username, password);
			this.statement = this.connection.createStatement();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			this.connection.close();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	public void execute(String query) {
		try {
			this.statement.execute(query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	
	public ResultSet get(String query) {
		try {
			return this.statement.executeQuery(query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
		
		return null;
	}
	
	public void update(String query) {
		try {
			this.statement.executeUpdate(query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
}
