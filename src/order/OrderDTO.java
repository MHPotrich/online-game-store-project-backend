package order;

import java.util.ArrayList;

import order.item.Item;
import profile.Profile;

public class OrderDTO {
	String id;
	Profile profile;
	int total = 0;
	int discount = 0;
	boolean isCompleted = false;
	ArrayList<Item> items = new ArrayList<Item>();
	
	OrderDTO(Order p_order) {
		this.id = p_order.getId().toString();
		this.profile = p_order.getProfile();
		this.total = p_order.getTotal();
		this.discount = p_order.getDiscount();
		this.isCompleted = p_order.getIsCompleted();
		this.items = p_order.getOrderItems();
	}
}
