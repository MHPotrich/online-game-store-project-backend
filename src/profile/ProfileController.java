package profile;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import dataBase.DataBase;
import order.Order;
import product.Game;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
	public static DataBase dataBase;
	
	@GetMapping("")
	public String listUsers() {
		Gson gson = new Gson();
		ArrayList<Profile> users = Profile.loadUsers(dataBase, "", "");
		
		return gson.toJson(users, ArrayList.class);
	}
	
	@GetMapping("/{id}")
	public String getUser(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		Profile user = new Profile();
		
		user.load(dataBase, p_id);
		
		return gson.toJson(user, Profile.class);
	}
	
	@PostMapping("")
	public String createUser(@RequestBody String p_body) {
		Profile newUser = new Gson().fromJson(p_body, Profile.class);
		
		newUser.save(dataBase);
		
		return new Gson().toJson(newUser, Profile.class);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") UUID p_id) {
		Profile user = new Profile();
		
		user.load(dataBase, p_id);
		user.delete(dataBase);
	}
	
	@PostMapping("/{profile_id}/cart/{game_id}")
	public String buy(@PathVariable("profile_id") UUID p_profile_id, @PathVariable("game_id") UUID p_game_id) {
		Gson gson = new Gson();
		Profile profile = new Profile();
		Game game = new Game();
		Order order = new Order(profile);
		
		profile.load(dataBase, p_profile_id);
		game.load(dataBase, p_game_id);
		
		// TODO: authenticate user before continue
		
		order.addItem(game, game.getActivePrice());
		
		order.save(dataBase);
		
		return gson.toJson(order, Order.class);
	}
}
