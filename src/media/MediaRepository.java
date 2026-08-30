package media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;

public class MediaRepository {
	public static DataBase dataBase;
	
	public static ArrayList<Media> findMediaById(UUID p_id) {
		String query = "SELECT * FROM medias WHERE id = '" + p_id.toString() + "' LIMIT 1;";
		ResultSet rawMedia = dataBase.get(query);
		ArrayList<Media> medias = new ArrayList<Media>();
		
		try {
			while (rawMedia.next()) {
				Media media = new Media(UUID.fromString(rawMedia.getString(1)));
				
				media.setUrl(rawMedia.getString(2));
				media.setDescription(rawMedia.getString(3));
				media.setType(rawMedia.getString(4));
				
				medias.add(media);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medias;
	}
	
	public static void saveMedia(Media p_media) {
		String query = "INSERT INTO midias (id, url, type, alt)";
		
		query = query + " VALUES (";
		query = query + "'" + p_media.getId().toString() + "'" + ", ";
		query = query + "'" + p_media.getUrl() + "'" + ", ";
		query = query + "'" + p_media.getType() + "'" + ", ";
		query = query + "'" + p_media.getDescription() + "'";
		query = query + ");";
		
		dataBase.execute(query);
	}
	
	public static void updateMedia(Media p_media) {
		String query = "UPDATE medias SET";
		
		query = query + " url = '" + p_media.getUrl() + "'";
		query = query + " type = '" + p_media.getType() + "'";
		query = query + " description = '" + p_media.getDescription() + "'";
		query = query + " WHERE id = " + p_media.getId().toString();
		
		dataBase.execute(query);
	}
	
	public static void deleteMediaById(UUID p_id) {
		String query = "DELETE FROM medias WHERE id = '" + p_id.toString() + "';";
		
		dataBase.update(query);
	}
}
