package profile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;
import product.Game;

public class Profile {
	private UUID id;
	private String firstName;
	private String lastName;
	private ArrayList<Game> library;
	private int wallet = 0;
	private int points = 0;
	private LocalDateTime creationDate;
	private String password;
	private String email;
	
	Profile(String p_firstName, String p_lastName) {
		this.id = java.util.UUID.randomUUID();
		this.firstName = p_firstName;
		this.lastName = p_lastName;
		this.creationDate = LocalDateTime.now();
	}
	
	Profile(UUID p_id, String p_firstName, String p_lastName) {
		this.id = p_id;
		this.firstName = p_firstName;
		this.lastName = p_lastName;
		this.creationDate = LocalDateTime.now();
	}
	
	public Profile() {
		this.id = java.util.UUID.randomUUID();
		this.creationDate = LocalDateTime.now();
	}
	
	public boolean buyGame(Game p_game) {
		if (this.wallet >= p_game.getActivePrice()) {
			this.library.add(p_game);
			this.wallet = this.wallet - p_game.getActivePrice();
			return true;
		}
		
		return false;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getPoints() {
		return points;
	}

	public UUID getId() {
		return id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public void setWallet(int p_wallet) {
		this.wallet = p_wallet;
	}
	
	public void setPoints(int p_points) {
		this.points = p_points;
	}
	
	public void load(DataBase p_dataBase, UUID p_id) {
		String query = "SELECT * FROM users WHERE id = '" + p_id.toString() + "' LIMIT 1;";
		ResultSet rawUser = p_dataBase.get(query);
		
		try {
			while (rawUser.next()) {
				this.id = UUID.fromString(rawUser.getString(1));
				this.firstName = rawUser.getString(2);
				this.lastName = rawUser.getString(3);
				this.wallet = rawUser.getInt(4);
				this.points = rawUser.getInt(5);
				this.email = rawUser.getString(6);
				this.password = rawUser.getString(7);
				this.creationDate = LocalDateTime.parse(rawUser.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save(DataBase p_dataBase) {
		String query = "INSERT INTO users (id, first_name, last_name, wallet, points, email, password, creation_date)";
		
		query = query + " VALUES (";
		query = query + "'" + this.id.toString() + "'" + ", ";
		query = query + "'" + this.firstName + "'" + ", ";
		query = query + "'" + this.lastName + "'" + ", ";
		query = query + this.wallet + ", ";
		query = query + this.points + ", ";
		query = query + "'" + this.email + "'" + ", ";
		query = query + "'" + this.password + "'" + ", ";
		query = query + this.creationDate.toString();
		query = query + ");";
		
		// TODO: save user game library
		
		p_dataBase.execute(query);
	}
	
	public void update(DataBase p_dataBase) {
		String query = "UPDATE users SET";
		
		query = query + " first_name = '" + this.firstName + "'";
		query = query + " last_name = '" + this.lastName + "'";
		query = query + " wallet = '" + this.wallet + "'";
		query = query + " points = '" + this.points + "'";
		query = query + " email = '" + this.email + "'";
		query = query + " password = '" + this.password + "'";
		query = query + " WHERE id = " + this.id.toString();
		
		// TODO: update user game library
		
		p_dataBase.execute(query);
	}
	
	public void delete(DataBase p_dataBase) {
		String query = "DELETE FROM users WHERE id = '" + this.id.toString() + "';";
		
		p_dataBase.update(query);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String p_password) {
		this.password = p_password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String p_email) {
		this.email = p_email;
	}
	
	private void setCreationDate(LocalDateTime p_creationDate) {
		this.creationDate = p_creationDate;
	}
	
	public static ArrayList<Profile> loadUsers(DataBase p_dataBase, String p_targetProperty, String p_targetValue) {
		ResultSet rawUsers;
		String query = "SELECT * FROM users";
		ArrayList<Profile> users = new ArrayList<Profile>();
		
		if(p_targetProperty.isEmpty() == false && p_targetValue.isEmpty() == false) {
			query = query + "WHERE " + p_targetProperty + " = " + p_targetValue + ";";
		}
		
		rawUsers = p_dataBase.get(query);
		
		try {
			while (rawUsers.next()) {
				UUID id = UUID.fromString(rawUsers.getString(1));
				String firstName = rawUsers.getString(2);
				String lastName = rawUsers.getString(3);
				int wallet = rawUsers.getInt(4);
				int points = rawUsers.getInt(5);
				String email = rawUsers.getString(6);
				String password = rawUsers.getString(7);
				LocalDateTime creationDate = LocalDateTime.parse(rawUsers.getString(8));
				Profile user = new Profile(id, firstName, lastName);
				
				user.setEmail(email);
				user.setPassword(password);
				user.setPoints(points);
				user.setWallet(wallet);
				user.setCreationDate(creationDate);
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void removeGame(UUID p_id) {
		for(Game game : this.library) {
			if (game.getId() == p_id) {
				this.library.remove(game);
				break;
			}
		}
	}
}
