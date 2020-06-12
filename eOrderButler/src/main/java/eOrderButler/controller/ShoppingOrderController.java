package eOrderButler.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eOrderButler.model.Item;
import eOrderButler.model.ShoppingOrder;
import eOrderButler.model.User;
import eOrderButler.service.ShoppingOrderService;
import eOrderButler.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class ShoppingOrderController {

	private static final String DATE_PATTERN = "yyyy-MM-dd";
	
	@Autowired
	private ShoppingOrderService shoppingOrderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getAllShoppingOrders", method = RequestMethod.GET)
	public 	@ResponseBody List<ShoppingOrder> getAllShoppingOrdersByTime(@RequestParam(value = "starting_date", required = false) @DateTimeFormat(pattern = DATE_PATTERN) Date startDate, 
																		@RequestParam(value = "ending_date", required = false) @DateTimeFormat(pattern = DATE_PATTERN) Date endDate) {
		int userId = getUser().getUserId();
		List<ShoppingOrder> orders = new ArrayList<>();
		if (startDate != null && endDate != null) {
			orders = shoppingOrderService.getAllShoppingOrdersByTime(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), userId);
		} else {
			orders = shoppingOrderService.getAllShoppingOrders(userId);
		}
		return orders;
	}
	
//	@RequestMapping(value = "/getShoppingOrderById/{orderId}", method = RequestMethod.GET)
//	public @ResponseBody ShoppingOrder getShoppingOrderById(@PathVariable(value = "orderId") int orderId) {
//		ShoppingOrder order = shoppingOrderService.getShoppingOrderById(orderId);
//		return order;
//	}
//	
//	@RequestMapping(value = "/deleteShoppingOrder/{orderId}", method = RequestMethod.GET)
//	public void deleteShoppingOrder(@PathVariable(value = "orderId") int orderId) {
//		shoppingOrderService.removeShoppingOrder(orderId);
//	}
	
	@RequestMapping(value = "/search/{itemName}", method = RequestMethod.GET) 
	public @ResponseBody List<ShoppingOrder> searchByItem(@PathVariable(value = "itemName") String itemName) {
		User user = getUser();
//		int userId = getUser().getUserId();
		List<ShoppingOrder> orders = shoppingOrderService.getOrdersByItemName(itemName, user);
		return orders;
	}
	
	@RequestMapping(value = "/addShoppingOrder", method = RequestMethod.POST)
	public ResponseEntity addShoppingOrder(@RequestBody String url) throws IOException {
		System.out.println("i am here at order controller");
		URL shoppingURL = new URL(url);
		String query = shoppingURL.getQuery();
		String[] params = query.split("?");
		for (String param : params) {
			String[] keyValuePair = param.split("=");
			if (keyValuePair[0] == "order_number") {
				
			}
		}
		ShoppingOrder order = parseURL(url);
		shoppingOrderService.addShoppingOrder(order);
		return ResponseEntity.ok().build();
	}
	
	private User getUser() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) loggedInUser.getPrincipal();
		String email = userDetail.getUsername();
		User user = userService.getUserByUserEmail(email);
		return user;
	}
	
	private ShoppingOrder parseURL(String url) throws IOException {
		
		ShoppingOrder shoppingOrder = new ShoppingOrder();
		shoppingOrder.setUser(getUser());
		String shoppingURL = getShoppingURL(url);
		
		StringBuilder responseBody = new StringBuilder();
		HttpURLConnection connection = (HttpURLConnection)new URL(shoppingURL).openConnection();
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			responseBody.append(line);
		}
		reader.close();
		
		System.out.println(responseBody.toString());
		JSONObject obj = new JSONObject(responseBody.toString());
		String status = "";
		if (!obj.isNull("tracking_info")) {
			JSONArray trackingInfo = obj.getJSONArray("tracking_info");
			JSONObject firstInfo = (JSONObject)trackingInfo.get(0);
			status = (String)firstInfo.get("status");
			System.out.println("status: " + status);
			shoppingOrder.setStatus(status);
			String merchant = (String)firstInfo.get("retailer_moniker");
			System.out.println("merchant: " + merchant);
			shoppingOrder.setMerchant(merchant);
		}
		
		if (!obj.isNull("order_info")) {
			JSONObject orderInfo = obj.getJSONObject("order_info");
			String orderNumber = (String)orderInfo.get("order_number");
			System.out.println("order number: " + orderNumber);
			shoppingOrder.setOrderNumber(orderNumber);
			String orderDate = (String)orderInfo.get("order_date");
			System.out.println("order date: " + orderDate);
			shoppingOrder.setDate(java.sql.Date.valueOf(orderDate.substring(0, 10)));
			JSONArray orderItems = orderInfo.getJSONArray("order_items");
			List<Item> items = new ArrayList<>();
			for (int i = 0; i < orderItems.length(); i++) {
				Item item = new Item();
				JSONObject itemInfo = orderItems.getJSONObject(i);
				String itemName = (String)itemInfo.get("name");
				System.out.println("item name: " + itemName);
				item.setItemName(itemName);
				int quantity = itemInfo.getInt("quantity");
				System.out.println("quantity:" + quantity);
				item.setQuantity(quantity);
				item.setOrder(shoppingOrder);
				item.setStatus(status);
				items.add(item);
			}
			shoppingOrder.setItems(items);
		}
		return shoppingOrder;
	}
	
	private String getShoppingURL(String url) throws IOException {
		URL shoppingUrl = new URL(url);
		StringBuilder res = new StringBuilder();
		res.append("http://");
		res.append(shoppingUrl.getHost());
		res.append("/tracking/itemvisibility/v1/sephora/orders/");
		String query = shoppingUrl.getQuery();
		String[] params = query.split("&");
		for (String param : params) {
			String[] keyValuePair = param.split("=");
			if (keyValuePair[0].equals("order_number")) {
				res.append(keyValuePair[1]);
				break;
			}
		}
		res.append("?");
		res.append(query);
		res.append("&tracking_url=");
		for (int i = 0; i < url.length(); i++) {
			char c = url.charAt(i);
			if (c == ':') {
				res.append("%3A");
			}
			else if (c == '/') {
				res.append("%2F");
			}
			else if (c == '?') {
				res.append("%3F");
			}
			else if (c == '=') {
				res.append("%3D");
			}
			else if (c == '&') {
				res.append("%26");
			} else {
				res.append(c);
			}	
		}
//		res.append("%20Request%20Method:%20GET");
		System.out.println(res.toString());
		return res.toString();
	}
}