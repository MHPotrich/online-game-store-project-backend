package media;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import product.Game;

@RestController
@RequestMapping("/media")
public class MediaController {
	@GetMapping("/{id}")
	public String getMedia(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		Media media = new Media();
		
		MediaRepository.findMediaById(p_id);
		
		return gson.toJson(media, Game.class);
	}
	
	@PostMapping("")
	public String createMedia(@RequestBody String p_body) {
		Media newMedia = new Gson().fromJson(p_body, Media.class);
		
		MediaRepository.saveMedia(newMedia);
		
		return new Gson().toJson(newMedia, Game.class);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMedia(@PathVariable("id") UUID p_id) {
		MediaRepository.deleteMediaById(p_id);
	}
}
