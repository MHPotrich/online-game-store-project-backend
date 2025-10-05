package mainPackage;

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

@RestController
@RequestMapping("/users")
public class UserController {
	public static DataBase dataBase;
	
	@GetMapping("")
	public String listUsers() {
		Gson gson = new Gson();
		ArrayList<User> users = User.loadUsers(dataBase, "", "");
		
		return gson.toJson(users, ArrayList.class);
	}
	
	@GetMapping("/{id}")
	public String getUser(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		User user = new User();
		
		user.load(dataBase, p_id);
		
		return gson.toJson(user, User.class);
	}
	
	@PostMapping("")
	public String createUser(@RequestBody String p_body) {
		User newUser = new Gson().fromJson(p_body, User.class);
		
		newUser.save(dataBase);
		
		return new Gson().toJson(newUser, User.class);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") UUID p_id) {
		User user = new User();
		
		user.load(dataBase, p_id);
		user.delete(dataBase);
	}
}
