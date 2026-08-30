package order.item;

import product.Game;

public class Item {
	private Integer id;
	private Game game;
	private int price = 0;
	
	public Item(int p_id, Game p_game, int p_price) {
		this.id = p_id;
		this.game = p_game;
		this.price = p_price;
	}
	
	public Item(int p_id, int p_price) {
		this.id = p_id;
		this.price = p_price;
	}
	
	public Integer getId() {		
		return this.id;
	}

	public int getPrice() {
		return this.price;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public void setGame(Game p_game) {
		this.game = p_game;
	}
}