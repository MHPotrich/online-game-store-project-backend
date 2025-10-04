package mainPackage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {
	
	@GetMapping("")
	public String getGames() {
		return "Hello World - Games";
	}
}
