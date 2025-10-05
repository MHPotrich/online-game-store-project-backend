package mainPackage;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/games")
public class GameController {
	
	public static DataBase dataBase;
	
	@GetMapping("/{id}")
	public String getGame(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		Game game = new Game("");
		
		System.out.println(p_id);
		
		game.load(dataBase, p_id);
		
		return gson.toJson(game, Game.class);
	}
	
	@GetMapping("")
	public String getGames() {
		Gson gson = new Gson();
		ArrayList<Game> games = Game.loadGames(dataBase, "", "");
		
		return gson.toJson(games, ArrayList.class);
	}
}
