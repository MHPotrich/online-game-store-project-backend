import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Game {
	private UUID id;
	private String title;
	private int listPrice = 0;
	private int salePrice = 0;
	private String coverImage;
	private boolean isActive = true;
	
	Game(String p_title) {
		this.id = java.util.UUID.randomUUID();
		this.title = p_title;
	}
	
	Game(String p_id, String p_title) {
		this.id = UUID.fromString(p_id);
		this.title = p_title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getActivePrice() {
		if (salePrice > 0) return salePrice;
		
		return listPrice;
	}
	
	public void setListPrice(int p_listPrice) {
		this.listPrice = p_listPrice;
	}
	
	public void setSalePrice(int p_salePrice) {
		this.salePrice = p_salePrice;
	}
	
	public String getCoverImageUrl() {
		return coverImage;
	}
	
	public void setCoverImage(String p_coverImage) {
		this.coverImage = p_coverImage;
	}

	public UUID getId() {
		return id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void load(DataBase dataBase) {
		
	}
	
	public void save(DataBase dataBase) {
		String query = "INSERT INTO games (id, title, cover_image, list_price, sale_price, is_active)";
		
		query = query + " VALUES (";
		query = query + "'" + this.id.toString() + "'" + ", ";
		query = query + "'" + this.title + "'" + ", ";
		query = query + "'" + this.coverImage + "'" + ", ";
		query = query + this.listPrice + ", ";
		query = query + this.salePrice + ", ";
		query = query + this.isActive;
		query = query + ");";
		
		//System.out.println(query);
		
		dataBase.execute(query);
	}
	
	public void update(DataBase dataBase) {
		
	}
	
	public void delete(DataBase dataBase) {
		
	}
	
	public static ArrayList<Game> loadGames(DataBase dataBase, String targetProperty, String targetValue) {
		ResultSet rawGames;
		String query = "SELECT * FROM Games";
		ArrayList<Game> games = new ArrayList<Game>();
		
		if(targetProperty.isEmpty() == false && targetValue.isEmpty() == false) {
			query = query + "WHERE " + targetProperty + " = " + targetValue + ";";
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
