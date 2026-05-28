package order;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import dataBase.DataBase;

@RestController
@RequestMapping("/order")
public class OrderController {
	public static DataBase dataBase;
	
	@GetMapping("/{id}")
	public String getOrder(@PathVariable("id") UUID p_id) {
		Gson gson = new Gson();
		Order order = new Order();
		
		order.load(dataBase, p_id);
		
		return gson.toJson(order, Order.class);
	}
}
