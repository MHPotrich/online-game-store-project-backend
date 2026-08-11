package profile;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

import product.Game;

public class Profile {
	private UUID id;
	private String firstName;
	private String lastName;
	private ArrayList<Game> library;
	private int wallet = 0;
	private int points = 0;
	private String creationDate;
	private String password;
	private String email;
	
	public Profile(String p_firstName, String p_lastName) {
		this.id = java.util.UUID.randomUUID();
		this.firstName = p_firstName;
		this.lastName = p_lastName;
		this.creationDate = Instant.now().toString();
		this.library = new ArrayList<Game>();
	}
	
	public Profile(UUID p_id, String p_firstName, String p_lastName) {
		this.id = p_id;
		this.firstName = p_firstName;
		this.lastName = p_lastName;
		this.creationDate = Instant.now().toString();
		this.library = new ArrayList<Game>();
	}
	
	public Profile() {
		this.id = java.util.UUID.randomUUID();
		this.creationDate = Instant.now().toString();
		this.library = new ArrayList<Game>();
	}
	
	public boolean buyGame(Game p_game) {
		if (this.wallet >= p_game.getActivePrice()) {
			this.library.add(p_game);
			this.wallet = this.wallet - p_game.getActivePrice();
			return true;
		}
		
		return false;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getPoints() {
		return points;
	}

	public UUID getId() {
		return id;
	}

	public String getCreationDate() {
		return creationDate;
	}
	
	public void setWallet(int p_wallet) {
		this.wallet = p_wallet;
	}
	
	public int getWallet() {
		return this.wallet;
	}
	
	public void setPoints(int p_points) {
		this.points = p_points;
	}
	
	public ArrayList<Game> getLibrary() {
		return this.library;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String p_password) {
		this.password = p_password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String p_email) {
		this.email = p_email;
	}
	
	public void setCreationDate(String p_creationDate) {
		this.creationDate = p_creationDate;
	}
	
	public void removeGame(UUID p_id) {
		for(Game game : this.library) {
			if (game.getId() == p_id) {
				this.library.remove(game);
				break;
			}
		}
	}
}
