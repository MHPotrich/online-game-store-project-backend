package product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;

public class GameRepository {
	public static DataBase dataBase;
	
	public static ArrayList<Game> findGameById(UUID p_id) {
		String query = "SELECT * FROM games WHERE id = '" + p_id.toString() + "' LIMIT 1;";
		ResultSet rawGame = dataBase.get(query);
		ArrayList<Game> games = new ArrayList<Game>();
		
		try {
			while (rawGame.next()) {
				Game game = new Game(rawGame.getString(1), rawGame.getString(2));
				
				game.setCoverImage(rawGame.getString(3));
				game.setListPrice(rawGame.getInt(4));
				game.setSalePrice(rawGame.getInt(5));
				game.setActive(rawGame.getBoolean(6));
				
				games.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return games;
	}
	
	public static void saveGame(Game p_game) {
		String query = "INSERT INTO games (id, title, cover_image, list_price, sale_price, is_active)";
		
		query = query + " VALUES (";
		query = query + "'" + p_game.getId().toString() + "'" + ", ";
		query = query + "'" + p_game.getTitle() + "'" + ", ";
		query = query + "'" + p_game.getCoverImageUrl() + "'" + ", ";
		query = query + p_game.getListPrice() + ", ";
		query = query + p_game.getSalePrice() + ", ";
		query = query + p_game.isActive();
		query = query + ");";
		
		dataBase.execute(query);
	}
	
	public static void updateGame(Game p_game) {
		String query = "UPDATE games SET";
		
		query = query + " title = '" + p_game.getTitle() + "'";
		query = query + " list_price = '" + p_game.getListPrice() + "'";
		query = query + " sale_price = '" + p_game.getSalePrice() + "'";
		query = query + " cover_image = '" + p_game.getCoverImageUrl() + "'";
		query = query + " is_active = '" + p_game.isActive() + "'";
		query = query + " WHERE id = " + p_game.getId().toString();
		
		dataBase.execute(query);
	}
	
	public static void deleteGameById(UUID p_id) {
		String query = "DELETE FROM games WHERE id = '" + p_id.toString() + "';";
		
		dataBase.update(query);
	}
	
	public static ArrayList<Game> getAllGames(String p_targetProperty, String p_targetValue) {
		ResultSet rawGames;
		String query = "SELECT * FROM Games";
		ArrayList<Game> games = new ArrayList<Game>();
		
		if(p_targetProperty.isEmpty() == false && p_targetValue.isEmpty() == false) {
			query = query + "WHERE " + p_targetProperty + " = " + p_targetValue + ";";
		}
		
		rawGames = dataBase.get(query);
		
		try {
			while (rawGames.next()) {
				String id = rawGames.getString(1);
				String title = rawGames.getString(2);
				String coverImage = rawGames.getString(3);
				Integer listPrice = rawGames.getInt(4);
				Integer salePrice = rawGames.getInt(5);
				Boolean isActive = rawGames.getBoolean(6);
				Game game = new Game(id, title);
				
				game.setCoverImage(coverImage);
				game.setListPrice(listPrice);
				game.setSalePrice(salePrice);
				game.setActive(isActive);
				
				games.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return games;
	}
}
