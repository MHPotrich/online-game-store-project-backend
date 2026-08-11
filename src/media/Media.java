package media;

import java.util.UUID;

public class Media {
	private UUID id;
	private String url;
	private String description;
	private String type;
	
	Media() {
		this.id = java.util.UUID.randomUUID();
	}
	
	Media(UUID p_id) {
		this.id = p_id;
	}
	
	Media(String p_url, String p_type) {
		this.id = java.util.UUID.randomUUID();
		this.url = p_url;
		this.type = p_type;
	}
	
	Media(String p_url, String p_description, String p_type) {
		this.id = java.util.UUID.randomUUID();
		this.url = p_url;
		this.description = p_description;
		this.type = p_type;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setUrl(String p_url) {
		this.url = p_url;
	}
	
	public void setDescription(String p_description) {
		this.description = p_description;
	}
	
	public void setType(String p_type) {
		this.type = p_type;
	}
}
