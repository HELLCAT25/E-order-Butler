package eOrderButler.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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

@Controller
public class ShoppingOrderController {

	@Autowired
	private ShoppingOrderService shoppingOrderService;
	private UserService userService;
	
	@RequestMapping(value = "/getAllShoppingOrders", method = RequestMethod.GET)
	public @ResponseBody List<ShoppingOrder> getAllShoppingOrders() {
		int userId = getUserId();
		List<ShoppingOrder> orders = shoppingOrderService.getAllShoppingOrders(userId);
		return orders;
	}
	
	
	@RequestMapping(value = "/getShoppingOrderById/{orderId}", method = RequestMethod.GET)
	public @ResponseBody ShoppingOrder getShoppingOrderById(@PathVariable(value = "orderId") int orderId) {
		ShoppingOrder order = shoppingOrderService.getShoppingOrderById(orderId);
		return order;
	}
	
	@RequestMapping(value = "/deleteShoppingOrder/{orderId}", method = RequestMethod.GET)
	public void deleteShoppingOrder(@PathVariable(value = "orderId") int orderId) {
		shoppingOrderService.removeShoppingOrder(orderId);
	}
	
	@RequestMapping(value = "/search/{itemName}", method = RequestMethod.GET) 
	public @ResponseBody List<ShoppingOrder> searchByItem(@PathVariable(value = "itemName") String itemName) {
		int userId = getUserId();
		List<ShoppingOrder> orders = shoppingOrderService.getOrdersByItemName(itemName, userId);
		return orders;
	}
	
	private int getUserId() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) loggedInUser.getPrincipal();
		User user = userService.getUserByUserEmail(userDetail.getUsername());
		return user.getUserId();
	}
	
	@RequestMapping(value = "/addShoppingOrder", method = RequestMethod.POST)
	public String addShoppingOrder(@RequestParam(value = "shoppingUrl", required = true) String url) throws IOException {
		ShoppingOrder order = parseURL(url);
		shoppingOrderService.addShoppingOrder(order);
		return "redirect:/getAllShoppingOrders";
	}
	
	private ShoppingOrder parseURL(String url) throws IOException {
		ShoppingOrder order = new ShoppingOrder();
		
		order.setuserId(getUserId());
		
		String shoppingURL = getShoppingURL(url);
		
		StringBuilder responseBody = new StringBuilder();
		HttpURLConnection connection = (HttpURLConnection)new URL(shoppingURL).openConnection();
		connection.setRequestMethod("GET");
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			responseBody.append(line);
		}
		reader.close();
		
		JSONObject obj = new JSONObject(responseBody.toString());
		String status = "";
		if (!obj.isNull("tracking_info")) {
			JSONArray trackingInfo = obj.getJSONArray("tracking_info");
			JSONObject firstInfo = (JSONObject)trackingInfo.get(0);
			status = (String)firstInfo.get("status");
			System.out.println("status: " + status);
			order.setStatus(status);
			String merchant = (String)firstInfo.get("retailer_moniker");
			System.out.println("merchant: " + merchant);
			order.setMerchant(merchant);
		}
		
		if (!obj.isNull("order_info")) {
			JSONObject orderInfo = obj.getJSONObject("order_info");
			String orderNumber = (String)orderInfo.get("order_number");
			System.out.println("order number: " + orderNumber);
			order.setOrderNumber(orderNumber);
			String orderDate = (String)orderInfo.get("order_date");
			System.out.println("order date: " + orderDate);
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
				item.setOrder(order);
				item.setStatus(status);
				items.add(item);
			}
			order.setItems(items);
		}
		
		return order;
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
		res.append("%20Request%20Method:%20GET");
		return res.toString();
	}
}

