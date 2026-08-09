package profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;

public class ProfileRepository {
	public static DataBase dataBase;
	
	// load method from profile class
	public static ArrayList<Profile> findProfileById(UUID p_id) {
		String query = "SELECT * FROM users WHERE id = '" + p_id.toString() + "' LIMIT 1;";
		ResultSet rawUser = dataBase.get(query);
		ArrayList<Profile> response = new ArrayList<Profile>();
		
		try {
			while (rawUser.next()) {
				Profile profile = new Profile(UUID.fromString(rawUser.getString(1)), rawUser.getString(2), rawUser.getString(3));

				profile.setWallet(rawUser.getInt(4));
				profile.setPoints(rawUser.getInt(5));
				profile.setEmail(rawUser.getString(6));
				profile.setPassword(rawUser.getString(7));
				profile.setCreationDate(rawUser.getString(8));
				
				response.add(profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	// save method from profile class
	public static void saveProfile(Profile p_profile) {
		String query = "INSERT INTO users (id, first_name, last_name, wallet, points, email, password, creation_date)";
		
		query = query + " VALUES (";
		query = query + "'" + p_profile.getId().toString() + "'" + ", ";
		query = query + "'" + p_profile.getFirstName() + "'" + ", ";
		query = query + "'" + p_profile.getLastName() + "'" + ", ";
		query = query + p_profile.getWallet() + ", ";
		query = query + p_profile.getPoints() + ", ";
		query = query + "'" + p_profile.getEmail() + "'" + ", ";
		query = query + "'" + p_profile.getPassword() + "'" + ", ";
		query = query + "'" + p_profile.getCreationDate() + "'";
		query = query + ");";
		
		// TODO: save user game library
		
		dataBase.execute(query);
	}
	
	// update method from profile class
	public static void updateProfile(Profile p_profile) {
		String query = "UPDATE users SET";
		
		query = query + " first_name = '" + p_profile.getFirstName() + "'";
		query = query + " last_name = '" + p_profile.getLastName() + "'";
		query = query + " wallet = '" + p_profile.getWallet() + "'";
		query = query + " points = '" + p_profile.getPoints() + "'";
		query = query + " email = '" + p_profile.getEmail() + "'";
		query = query + " password = '" + p_profile.getPassword() + "'";
		query = query + " WHERE id = " + p_profile.getId().toString();
		
		// TODO: update user game library
		
		dataBase.execute(query);
	}
	
	// delete method from profile class
	public static void deleteProfileById(UUID p_id) {
		String query = "DELETE FROM users WHERE id = '" + p_id.toString() + "';";
		
		dataBase.update(query);
	}
	
	// loadUsers method from profile class
	public static ArrayList<Profile> findAllProfiles(String p_targetProperty, String p_targetValue) {
		ResultSet rawUsers;
		String query = "SELECT * FROM users";
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		
		if(p_targetProperty.isEmpty() == false && p_targetValue.isEmpty() == false) {
			query = query + "WHERE " + p_targetProperty + " = " + p_targetValue + ";";
		}
		
		rawUsers = dataBase.get(query);
		
		try {
			while (rawUsers.next()) {
				UUID id = UUID.fromString(rawUsers.getString(1));
				String firstName = rawUsers.getString(2);
				String lastName = rawUsers.getString(3);
				int wallet = rawUsers.getInt(4);
				int points = rawUsers.getInt(5);
				String email = rawUsers.getString(6);
				String password = rawUsers.getString(7);
				String creationDate = rawUsers.getString(8);
				Profile profile = new Profile(id, firstName, lastName);
				
				profile.setEmail(email);
				profile.setPassword(password);
				profile.setPoints(points);
				profile.setWallet(wallet);
				profile.setCreationDate(creationDate);
				
				profiles.add(profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return profiles;
	}
}
