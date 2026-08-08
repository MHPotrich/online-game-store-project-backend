package product;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/games")
public class GameController {
	public static DataBase dataBase;
	
	@Operation(
    		summary = "Get all registered games",
    		description = ""
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "return a list of games")
    })
	@GetMapping("")
	public String listGames() {
		Gson gson = new Gson();
		ArrayList<Game> games = Game.loadGames(dataBase, "", "");
		
		return gson.toJson(games, ArrayList.class);
	}
	
	@Operation(
    		summary = "Get a game by ID",
    		description = ""
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "return the game with the same ID."),
        @ApiResponse(responseCode = "404", description = "Not found game with the specified ID.")
    })
	@GetMapping("/{id}")
	public String getGame(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		Game game = new Game();
		
		game.load(dataBase, p_id);
		
		// TODO: create response for when the game doesn't exists
		
		return gson.toJson(game, Game.class);
	}
	
	@Operation(
    		summary = "Create a Game",
    		description = ""
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Game was successfuly created."),
        @ApiResponse(responseCode = "422", description = "Not valid game."),
        @ApiResponse(responseCode = "500", description = "Internal error.")
    })
	@PostMapping("")
	public String createGame(@RequestBody String p_body) {
		Game newGame = new Gson().fromJson(p_body, Game.class);
		
		newGame.save(dataBase);
		
		return new Gson().toJson(newGame, Game.class);
	}
	
	@Operation(
    		summary = "Delete a Game",
    		description = ""
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Game was successfuly deleted."),
        @ApiResponse(responseCode = "404", description = "Not found game with the specified ID."),
        @ApiResponse(responseCode = "500", description = "Internal error.")
    })
	@DeleteMapping("/{id}")
	public void deleteGame(@PathVariable("id") UUID p_id) {
		Game game = new Game();
		
		game.load(dataBase, p_id);
		game.delete(dataBase);
		
		// TODO: create response for when the specified game doesn't exists
	}
}
