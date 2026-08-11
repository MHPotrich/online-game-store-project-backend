package order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;
import profile.Profile;
import profile.ProfileRepository;

public class OrderRepository {
	public static DataBase dataBase;
	
	// load method from Order class
	public static ArrayList<Order> findOrderById(UUID p_id) {
		String query = "SELECT * FROM orders WHERE user_id = '" + p_id.toString() + "' LIMIT 1;";
		ResultSet rawOrder = dataBase.get(query);
		ArrayList<Order> orders = new ArrayList<Order>();
		
		try {
			while (rawOrder.next()) {
				UUID profile_id = UUID.fromString(rawOrder.getString(2));
				Profile profile = ProfileRepository.findProfileById(profile_id).getFirst();
				Order order = new Order(UUID.fromString(rawOrder.getString(1)), profile, LocalDateTime.now());
				
				order.setTotal(rawOrder.getInt(3));
				order.setCompleted(rawOrder.getBoolean(4));
				
				//this.items = loadAllItems(p_dataBase);
				
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public static void saveOrder(Order p_order) {
		
	}
}
