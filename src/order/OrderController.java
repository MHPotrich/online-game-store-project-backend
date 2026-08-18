package order;

import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import product.Game;
import product.GameRepository;
import profile.Profile;
import profile.ProfileRepository;

@RestController
@RequestMapping("/order")
public class OrderController {
	private class CreateRequestBody {
		String profileId;
	}
	
	private class AddRequestBody {
		String gameId;
	}
	
	@Operation(
    		summary = "Get order by ID",
    		description = "return order"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Return order with the same ID."),
        @ApiResponse(responseCode = "404", description = "Return when order with specified ID is not found.")
    })
	@GetMapping("/{id}")
	public String getOrder(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		Order order = OrderRepository.findOrderById(p_id).getFirst();
		
		return gson.toJson(order, Order.class);
	}
	
	@Operation(
    		summary = "Get order by ID",
    		description = "return order"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Return order with the same ID."),
        @ApiResponse(responseCode = "404", description = "Return when order with specified ID is not found.")
    })
	@PostMapping("")
	public String createOrder(@RequestBody String p_body) {
		CreateRequestBody body = new Gson().fromJson(p_body, CreateRequestBody.class);
		Profile profile = ProfileRepository.findProfileById(UUID.fromString(body.profileId)).getFirst();
		Order order = new Order(profile);
		
		// TODO: save profile and order

		return Map.of("id", order.getId().toString()).toString();
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
    @PostMapping("/{order_id}/add")
    public String buy(
        @PathVariable("order_id") UUID p_order_id,
        @RequestBody String p_body
    ) {
		Gson gson = new Gson();
		AddRequestBody body = gson.fromJson(p_body, AddRequestBody.class);
        Order order = OrderRepository.findOrderById(p_order_id).getFirst();
        Game game = GameRepository.findGameById(UUID.fromString(body.gameId)).getFirst();

        // TODO: authenticate user before continue

        order.addItem(game, game.getActivePrice());

        OrderRepository.saveOrder(order);

        return gson.toJson(order, Order.class);
    }
}
