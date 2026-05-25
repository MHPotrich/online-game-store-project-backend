package order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;
import payment.Payment;
import profile.Profile;

public class Order {
	
	// TODO: create data base table for order items
	private class Item {
		private String name;
		private int price = 0;
		
		Item(String p_name, int p_price) {
			this.name = p_name;
			this.price = p_price;
		}

		public String getName() {
			return name;
		}

		public int getPrice() {
			return price;
		}
		
		public void load() {
			// TODO: load from the data base
		}
		
		public void save() {
			// TODO: save in the data base
		}
		
		public void update() {
			// TODO: update existing item in the data base
		}
	}
	
	private UUID id;
	private Profile profile;
	private int total = 0;
	private boolean isCompleted = false;
	private ArrayList<Item> items;
	private LocalDateTime creationDate;
	private Payment paymentMethod;
	
	Order() {
		this.id = java.util.UUID.randomUUID();
		this.creationDate = LocalDateTime.now();
		
	}
	
	Order(Profile p_profile) {
		this.id = java.util.UUID.randomUUID();
		this.profile = p_profile;
		this.creationDate = LocalDateTime.now();
	}
	
	public void addItem(String p_name, int p_price) {
		Item item = new Item(p_name, p_price);
		
		items.add(item);
		total += item.getPrice();
	}
	
	public boolean placeOrder() {
		if (!isCompleted) {
			// do place order logic
			this.isCompleted = false;
			return true;
		}
		
		return false;
	}

	public UUID getId() {
		return id;
	}

	public Profile getProfile() {
		return profile;
	}

	public int getTotal() {
		return total;
	}
	
	public ArrayList<Item> getOrderItems() {
		return items;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public void load(DataBase p_dataBase, UUID p_id) {
		String query = "SELECT * FROM orders WHERE user_id = '" + p_id.toString() + "' LIMIT 1;";
		ResultSet rawOrder = p_dataBase.get(query);
		
		try {
			while (rawOrder.next()) {
				this.id = UUID.fromString(rawOrder.getString(1));
				
				UUID user_id = UUID.fromString(rawOrder.getString(2));
				this.profile = new Profile();
				profile.load(p_dataBase, user_id);;
				
				this.total = rawOrder.getInt(3);
				this.isCompleted = rawOrder.getBoolean(4);
				
				// TODO: load items from the order
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save(DataBase dataBase) {
		// TODO: save order in the data base
	}
	
	public void update(DataBase dataBase) {
		// TODO: update existing order in the data base
	}
	
	public void delete(DataBase dataBase) {
		// TODO: delete existing order in the data base
	}
	
	public void close() {
		// TODO: check payment and product is available to buy
		this.isCompleted = true;
	}
}
