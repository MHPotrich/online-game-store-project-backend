package order;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/order")
public class OrderController {
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
}
