package profile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dataBase.DataBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.ArrayList;
import java.util.UUID;
import order.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.Game;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    public static DataBase dataBase;
    
    private JsonObject convertProfileToJson(Profile profile) {
    	JsonObject jsonProfile = new JsonObject();
    	
    	jsonProfile.addProperty("firstName", profile.getFirstName());
    	jsonProfile.addProperty("lastName", profile.getLastName());
    	jsonProfile.addProperty("email", profile.getEmail());
    	jsonProfile.addProperty("id", profile.getId().toString());
        
        JsonArray games = new JsonArray();
        
        for(Game game: profile.getLibrary()) {
        	JsonObject jsonGame = new JsonObject();
        	
        	jsonGame.addProperty("id", game.getId().toString());
        	jsonGame.addProperty("title", game.getTitle());
        	jsonGame.addProperty("coverImage", game.getCoverImageUrl());
        }
        
        jsonProfile.add("library", games);
        
        return jsonProfile;
    }

    @Operation(
    		summary = "List all profiles",
    		description = "Return a list of profiles"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Return profiles.")
    })
    @GetMapping("")
    public String listProfiles() {
        Gson gson = new Gson();
        ArrayList<Profile> profiles = ProfileRepository.findAllProfiles("", "");
        JsonArray profilesJson = new JsonArray();
        
        for(Profile profile: profiles) {
        	profilesJson.add(convertProfileToJson(profile));
        }

        return gson.toJson(profiles, ArrayList.class);
    }

    @Operation(
    		summary = "Get profile by ID",
    		description = "return profile"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Return profile with the same ID."),
        @ApiResponse(responseCode = "404", description = "Return when profile with specified ID is not found.")
    })
    @GetMapping("/{id}")
    public String getProfile(@Parameter(description = "Profile ID") @PathVariable("id") UUID p_id) {
        Gson gson = new Gson();
        Profile profile = ProfileRepository.findProfileById(p_id).getFirst();

        return gson.toJson(convertProfileToJson(profile));
    }

    @Operation(
    		summary = "Create profile",
    		description = "return profile"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Return profile"),
        @ApiResponse(responseCode = "422", description = "Invalid profile"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping("")
    public String createProfile(@RequestBody String p_body) {
    	Gson gson = new Gson();
        Profile newProfile = new Gson().fromJson(p_body, Profile.class);

        ProfileRepository.saveProfile(newProfile);

        return gson.toJson(convertProfileToJson(newProfile));
    }

    @Operation(
    		summary = "Delete a profile",
    		description = ""
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profile deleted successfuly"),
        @ApiResponse(responseCode = "404", description = "Invalid profile"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable("id") UUID p_id) {
        ProfileRepository.deleteProfileById(p_id);
    }

    @Operation(
    		summary = "Buy item",
    		description = "return order"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Return order"),
        @ApiResponse(responseCode = "422", description = "Invalid game or profile"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping("/{profile_id}/cart/{game_id}")
    public String buy(
        @PathVariable("profile_id") UUID p_profile_id,
        @PathVariable("game_id") UUID p_game_id
    ) {
        Gson gson = new Gson();
        Profile profile = ProfileRepository.findProfileById(p_profile_id).getFirst();
        Game game = new Game();
        Order order = new Order(profile);

        // TODO: authenticate user before continue

        order.addItem(game, game.getActivePrice());

        order.save(dataBase);

        return gson.toJson(order, Order.class);
    }
}
