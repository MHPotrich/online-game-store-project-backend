package order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dataBase.DataBase;
import order.item.Item;
import order.item.ItemRepository;
import profile.Profile;
import profile.ProfileRepository;

public class OrderRepository {
	public static DataBase dataBase;
	
	// load method from Order class
	public static ArrayList<Order> findOrderById(UUID p_id) {
		String query = "SELECT * FROM orders WHERE id = '" + p_id.toString() + "' LIMIT 1;";
		ResultSet rawOrder = dataBase.get(query);
		ArrayList<Order> orders = new ArrayList<Order>();
		
		try {
			Map<UUID, UUID> cacheResultSets = new HashMap<UUID, UUID>();
			
			while (rawOrder.next()) {
				UUID order_id = UUID.fromString(rawOrder.getString(1));
				UUID profile_id = UUID.fromString(rawOrder.getString(2));
				int total = rawOrder.getInt(3);
				boolean isCompleted = rawOrder.getBoolean(4);
				Order order = new Order(order_id, LocalDateTime.now());
				
				cacheResultSets.put(order_id, profile_id);
				
				order.setTotal(total);
				order.setCompleted(isCompleted);
				
				orders.add(order);
			}
			
			for (Order order : orders) {
				UUID profile_id = cacheResultSets.get(order.getId());
				Profile profile = ProfileRepository.findProfileById(profile_id).getFirst();
				ArrayList<Item> items = ItemRepository.findItemsByOrderId(order.getId());
				
				order.setProfile(profile);
				order.updateItems(items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public static void createOrder(Order p_order) {
		String query = "INSERT INTO orders (id, user_id, total, discount, placed_order, creation_date, payment)";
		
		query = query + " VALUES (";
		query = query + "'" + p_order.getId().toString() + "'" + ", ";
		query = query + "'" + p_order.getProfile().getId().toString() + "'" + ", ";
		query = query + p_order.getTotal() + ", ";
		query = query + p_order.getDiscount() + ", ";
		query = query + p_order.getIsCompleted() + ", ";
		query = query + "'" + p_order.getCreationDate().toString() + "'" + ", ";
		query = query + "'" + UUID.randomUUID() + "'";
		query = query + ");";
		
		dataBase.execute(query);
	}
	
	public static void updateOrder(Order p_order) {
		String query = "UPDATE orders SET";
		
		query = query + " total = " + p_order.getTotal() + ",";
		query = query + " discount = " + p_order.getDiscount() + ",";
		query = query + " placed_order = " + p_order.getIsCompleted();
		query = query + " WHERE id = '" + p_order.getId().toString() + "';";
		
		dataBase.execute(query);
	}
	
	public static void deleteOrderById(UUID p_id) {
		String query = "DELETE FROM orders WHERE id = '" + p_id.toString() + "';";
		
		dataBase.update(query);
	}
}
