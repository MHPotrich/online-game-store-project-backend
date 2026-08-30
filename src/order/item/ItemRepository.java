package order.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dataBase.DataBase;
import product.Game;
import product.GameRepository;

public class ItemRepository {
	public static DataBase dataBase;
	
	private static ArrayList<Item> findItemsByQuery(String p_query) {
		ResultSet rawOrder = dataBase.get(p_query);
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			Map<Integer, UUID> cacheResultSets = new HashMap<Integer, UUID>();
			
			while (rawOrder.next()) {
				UUID game_id = UUID.fromString(rawOrder.getString(2));
				Item item = new Item(rawOrder.getInt(1), rawOrder.getInt(3));
				
				cacheResultSets.put(rawOrder.getInt(1), game_id);
				
				items.add(item);
			}
			
			for (Item item : items) {
				UUID game_id = cacheResultSets.get(item.getId());
				Game game = GameRepository.findGameById(game_id).getFirst();
				
				item.setGame(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	public static ArrayList<Item> findItemById(Integer p_id){
		String query = "SELECT * FROM order_item WHERE id = " + p_id.toString() + ";";
		
		return findItemsByQuery(query);
	}

	public static ArrayList<Item> findItemsByOrderId(UUID p_orderId) {
		String query = "SELECT * FROM order_item WHERE order_id = '" + p_orderId.toString() + "';";
		
		return findItemsByQuery(query);
	}
	
	public static void deleteItemById(Integer p_id) {
		String queryItem = "DELETE FROM order_item WHERE id = " + p_id.toString() + ";";

		dataBase.update(queryItem);
	}
	
	public static void createItem(Item p_item, UUID p_orderId) {
		String query = "INSERT INTO order_item (id, game_id, order_id, total)";
		
		query = query + " VALUES (";
		query = query + + p_item.getId() + ", ";
		query = query + "'" + p_item.getGame().getId().toString() + "'" + ", ";
		query = query + "'" + p_orderId.toString() + "'" + ", ";
		query = query + p_item.getPrice();
		query = query + ");";
		
		dataBase.execute(query);
	}
	
	public static void updateItem(Item p_item) {
		String query = "UPDATE order_item SET";
		
		query = query + " total = '" + p_item.getPrice() + "'";
		query = query + " WHERE id = " + p_item.getId();
		
		dataBase.execute(query);
	}
}
