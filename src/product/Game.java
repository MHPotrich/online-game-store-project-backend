package product;
import java.util.UUID;

public class Game {
	private UUID id;
	private String title;
	private int listPrice = 0;
	private int salePrice = 0;
	private String coverImage;
	private boolean isActive = true;
	
	Game(String p_title) {
		this.id = java.util.UUID.randomUUID();
		this.title = p_title;
	}
	
	Game(String p_id, String p_title) {
		this.id = UUID.fromString(p_id);
		this.title = p_title;
	}
	
	public Game() {
		this.id = java.util.UUID.randomUUID();
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getActivePrice() {
		if (salePrice > 0) return salePrice;
		
		return listPrice;
	}
	
	public void setListPrice(int p_listPrice) {
		this.listPrice = p_listPrice;
	}
	
	public void setSalePrice(int p_salePrice) {
		this.salePrice = p_salePrice;
	}
	
	public String getCoverImageUrl() {
		return coverImage;
	}
	
	public void setCoverImage(String p_coverImage) {
		this.coverImage = p_coverImage;
	}

	public UUID getId() {
		return id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public int getListPrice() {
		return this.listPrice;
	}
	
	public int getSalePrice() {
		return this.salePrice;
	}
}
