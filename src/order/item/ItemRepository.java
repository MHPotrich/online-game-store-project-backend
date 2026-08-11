package order.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;
import product.Game;
import product.GameRepository;

public class ItemRepository {
	public static DataBase dataBase;
	
	public static ArrayList<Item> findItemById(Integer p_id){
		String query = "SELECT * FROM order_item WHERE id = '" + p_id.toString() + ";";
		ResultSet rawOrder = dataBase.get(query);
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			while (rawOrder.next()) {
				// TODO: load game from database
				Game game = GameRepository.findGameById(UUID.fromString(rawOrder.getString(2))).getFirst();
				Item item = new Item(rawOrder.getInt(1), game, rawOrder.getInt(3));
				
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}

	public static ArrayList<Item> findItemsByOrderId(UUID p_orderId) {
		String query = "SELECT * FROM order_items WHERE order_id = '" + p_orderId.toString() + ";";
		ResultSet rawOrder = dataBase.get(query);
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			while (rawOrder.next()) {
				Item item = findItemById(rawOrder.getInt(1)).getFirst();
				
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	public static void deleteItemById(Integer p_id) {
		String queryItem = "DELETE FROM order_item WHERE id = " + p_id.toString() + ";";
		String queryItemRelation = "DELETE FROM order_items WHERE item_id = " + p_id.toString() + ";";
		
		
		dataBase.update(queryItemRelation);
		dataBase.update(queryItem);
	}
}
