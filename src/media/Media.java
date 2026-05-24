package media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import dataBase.DataBase;

public class Media {
	private UUID id;
	private String url;
	private String description;
	private String type;
	
	Media() {
		this.id = java.util.UUID.randomUUID();
	}
	
	Media(String p_url, String p_type) {
		this.id = java.util.UUID.randomUUID();
		this.url = p_url;
		this.type = p_type;
	}
	
	Media(String p_url, String p_description, String p_type) {
		this.id = java.util.UUID.randomUUID();
		this.url = p_url;
		this.description = p_description;
		this.type = p_type;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void load(DataBase p_dataBase, UUID p_id) {
		String query = "SELECT * FROM medias WHERE id = '" + p_id.toString() + "' LIMIT 1;";
		ResultSet rawMedia = p_dataBase.get(query);
		
		try {
			while (rawMedia.next()) {
				this.id = UUID.fromString(rawMedia.getString(1));
				this.url = rawMedia.getString(2);
				this.description = rawMedia.getString(3);
				this.type = rawMedia.getString(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save(DataBase p_dataBase) {
		String query = "INSERT INTO games (id, url, type, alt)";
		
		query = query + " VALUES (";
		query = query + "'" + this.id.toString() + "'" + ", ";
		query = query + "'" + this.url + "'" + ", ";
		query = query + "'" + this.type + "'" + ", ";
		query = query + "'" + this.description + "'";
		query = query + ");";
		
		//System.out.println(query);
		
		p_dataBase.execute(query);
	}
	
	public void update(DataBase p_dataBase) {
		
	}
	
	public void delete(DataBase p_dataBase) {
		String query = "DELETE FROM medias WHERE id = '" + this.id.toString() + "';";
		
		p_dataBase.update(query);
	}
}
