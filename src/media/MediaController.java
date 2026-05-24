package media;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import dataBase.DataBase;
import product.Game;

@RestController
@RequestMapping("/media")
public class MediaController {
	public static DataBase dataBase;
	
	@GetMapping("/{id}")
	public String getMedia(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		Media media = new Media();
		
		media.load(dataBase, p_id);
		
		return gson.toJson(media, Game.class);
	}
	
	@PostMapping("")
	public String createMedia() {
		
	}
}
