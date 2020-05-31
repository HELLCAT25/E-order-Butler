package eOrderButler.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eOrderButler.model.ShoppingOrder;
import eOrderButler.service.ShoppingOrderService;

@Controller
public class ShoppingOrderController {

	private static final String DATE_PATTERN = "yyyy-MM-dd";
	@Autowired
	private ShoppingOrderService shoppingOrderService;
	
	@RequestMapping(value = "/getAllShoppingOrders/{userId}", method = RequestMethod.GET)
	public @ResponseBody List<ShoppingOrder> getAllShoppingOrders(@PathVariable(value = "orderId") int userId) {
		List<ShoppingOrder> orders = shoppingOrderService.getAllShoppingOrders(userId);
		return orders;
	}
	
	
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
	
	@RequestMapping(value = "/getAllShoppingOrders", method = RequestMethod.GET)
	@ResponseBody
	public List<ShoppingOrder> getAllShoppingOrdersByTime(@RequestParam(value = "starting_date") @DateTimeFormat(pattern = DATE_PATTERN) Date startDate, 
															@RequestParam("ending_date") @DateTimeFormat(pattern = DATE_PATTERN) Date endDate) {
		System.out.println("hi im here now");
		if (startDate != null && endDate != null) {
			return shoppingOrderService.getAllShoppingOrdersByTime(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
		}
		return null;
	}
	//return specific number of orders
}
