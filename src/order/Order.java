package order;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import dataBase.DataBase;
import order.item.Item;
import payment.Payment;
import product.Game;
import profile.Profile;

public class Order {
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
	
	public Order(UUID p_id, Profile p_profile, LocalDateTime p_creationDate) {
		this.id = p_id;
		this.profile = p_profile;
		this.creationDate = p_creationDate;
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
	
	public void setTotal(int p_total) {
		this.total = p_total;
	}
	
	public void setCompleted(Boolean p_isCompleted) {
		this.isCompleted = p_isCompleted;
	}
	
	public ArrayList<Item> getOrderItems() {
		return items;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public void close(DataBase p_dataBase) {
		// TODO: check payment and product is available to buy
		this.isCompleted = true;
		OrderRepository.saveOrder(this);
	}
}
