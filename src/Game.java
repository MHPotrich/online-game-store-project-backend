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
	
	public String getTitle() {
		return title;
	}
	
	public int getActivePrice() {
		if (salePrice > 0) return salePrice;
		
		return listPrice;
	}
	
	public String getCoverImageUrl() {
		return coverImage;
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
}
