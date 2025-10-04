package mainPackage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class User {
	private UUID id;
	private String firstName;
	private String lastName;
	private ArrayList<Game> library;
	private int wallet = 0;
	private int points = 0;
	private LocalDateTime creationDate;
	private String password;
	private String email;
	
	User(String p_firstName, String p_lastName) {
		this.id = java.util.UUID.randomUUID();
		this.firstName = p_firstName;
		this.lastName = p_lastName;
		this.creationDate = LocalDateTime.now();
	}
	
	public boolean buyGame(Game game) {
		if (this.wallet >= game.getActivePrice()) {
			library.add(game);
			this.wallet = this.wallet - game.getActivePrice();
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public void load(DataBase dataBase) {
		
	}
	
	public void save(DataBase dataBase) {
		
	}
	
	public void update(DataBase dataBase) {
		
	}
	
	public void delete(DataBase dataBase) {
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
