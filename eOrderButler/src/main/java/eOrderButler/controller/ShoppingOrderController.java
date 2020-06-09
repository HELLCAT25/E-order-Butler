package eOrderButler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import eOrderButler.model.ShoppingOrder;
import eOrderButler.service.ShoppingOrderService;

@Controller
public class ShoppingOrderController {

	@Autowired
	private ShoppingOrderService shoppingOrderService;
	
	@RequestMapping(value = "/getAllShoppingOrders/{userId}", method = RequestMethod.GET)
	public @ResponseBody List<ShoppingOrder> getAllShoppingOrders(@PathVariable(value = "orderId") int userId) {
		List<ShoppingOrder> orders = shoppingOrderService.getAllShoppingOrders(userId);
		return orders;
	}
	
	
	@CrossOrigin(origins="http://localhost:3001")
	@RequestMapping(value = "/getShoppingOrderById/{orderId}", method = RequestMethod.GET)
	public @ResponseBody ShoppingOrder getShoppingOrderById(@PathVariable(value = "orderId") int orderId) {
		ShoppingOrder order = shoppingOrderService.getShoppingOrderById(orderId);
		return order;
	}
	
	@RequestMapping(value = "/deleteShoppingOrder/{userId}/{orderId}", method = RequestMethod.GET)
	public String deleteShoppingOrder(@PathVariable(value = "orderId") int orderId, @PathVariable(value = "userId") int userId) {
		shoppingOrderService.removeShoppingOrder(orderId);
		return String.format("redirect:/getAllShoppingOrders/{%s}", userId);
	}
}
