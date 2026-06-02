package order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;
import payment.Payment;
import product.Game;
import profile.Profile;

public class Order {
	
	// TODO: create data base table for order items
	private class Item {
		private Integer id;
		private Game game;
		private int price = 0;
		
		Item(Game p_game, int p_price) {
			this.game = p_game;
			this.price = p_price;
		}
		
		Item() {
			
		}

		public int getPrice() {
			return this.price;
		}
		
		public Game getGame() {
			return this.game;
		}
		
		public void load(DataBase p_dataBase, Integer p_orderId) {
			String query = "SELECT * FROM order_items WHERE order_id = '" + p_orderId.toString() + "' LIMIT 1;";
			ResultSet rawOrder = p_dataBase.get(query);
			
			try {
				while (rawOrder.next()) {
					Game game = new Game();
					
					game.load(p_dataBase, UUID.fromString(rawOrder.getString(2)));
					
					this.id = rawOrder.getInt(1);
					this.game = game;
					this.price = rawOrder.getInt(3);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void save() {
			// TODO: save in the data base
		}
		
		public void update() {
			// TODO: update existing item in the data base
		}
		
		public void delete(DataBase p_dataBase) {
			String queryItem = "DELETE FROM order_item WHERE id = " + this.id.toString() + ";";
			String queryItemRelation = "DELETE FROM order_items WHERE item_id = " + this.id.toString() + ";";
			
			
			p_dataBase.update(queryItem);
			p_dataBase.update(queryItemRelation);
		}
	}
	
	private UUID id;
	private Profile profile;
	private int total = 0;
	private int discount = 0;
	private boolean isCompleted = false;
	private ArrayList<Item> items;
	private LocalDateTime creationDate;
	private Payment paymentMethod;
	
	public Order() {
		this.id = java.util.UUID.randomUUID();
		this.creationDate = LocalDateTime.now();
	}
	
	public Order(Profile p_profile) {
		this.id = java.util.UUID.randomUUID();
		this.profile = p_profile;
		this.creationDate = LocalDateTime.now();
	}
	
	public void addItem(Game p_game, int p_price) {
		Item item = new Item(p_game, p_price);
		
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
	
	private ArrayList<Item> loadAllItems(DataBase p_dataBase) {
		String query = "SELECT * FROM order_items WHERE order_id = '" + this.id.toString() + ";";
		ResultSet rawOrder = p_dataBase.get(query);
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			while (rawOrder.next()) {
				Item item = new Item();
				
				item.load(p_dataBase, rawOrder.getInt(1));
				
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
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
				
				this.items = loadAllItems(p_dataBase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save(DataBase p_dataBase) {
		// TODO: save order in the data base
	}
	
	public void update(DataBase p_dataBase) {
		// TODO: update existing order in the data base
	}
	
	public void delete(DataBase p_dataBase) {
		// TODO: delete existing order in the data base
	}
	
	public void close(DataBase p_dataBase) {
		// TODO: check payment and product is available to buy
		this.isCompleted = true;
		this.save(p_dataBase);
	}
}
