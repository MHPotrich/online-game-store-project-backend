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
@RequestMapping("/games")
public class GameController {
	public static DataBase dataBase;
	
	@GetMapping("")
	public String getGames() {
		Gson gson = new Gson();
		ArrayList<Game> games = Game.loadGames(dataBase, "", "");
		
		return gson.toJson(games, ArrayList.class);
	}
	
	@GetMapping("/{id}")
	public String getGame(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		Game game = new Game();
		
		game.load(dataBase, p_id);
		
		return gson.toJson(game, Game.class);
	}
	
	@PostMapping("")
	public String createGame(@RequestBody String p_body) {
		Game newGame = new Gson().fromJson(p_body, Game.class);
		
		newGame.save(dataBase);
		
		return new Gson().toJson(newGame, Game.class);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGame(@PathVariable("id") UUID p_id) {
		Game game = new Game();
		
		game.load(dataBase, p_id);
		game.delete(dataBase);
	}
}
