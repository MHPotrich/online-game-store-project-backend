import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Transaction {
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
	}
	
	private UUID id;
	private User user;
	private int total = 0;
	private boolean isCompleted = false;
	private ArrayList<Item> items;
	private LocalDateTime creationDate;
	
	Transaction(User p_user) {
		this.id = java.util.UUID.randomUUID();
		this.user = p_user;
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

	public User getUser() {
		return user;
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
}
