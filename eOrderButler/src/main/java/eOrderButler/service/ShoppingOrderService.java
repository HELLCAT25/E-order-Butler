package eOrderButler.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eOrderButler.dao.ShoppingOrderDao;
import eOrderButler.model.ShoppingOrder;
import eOrderButler.model.User;

@Service
public class ShoppingOrderService {

	@Autowired
	private ShoppingOrderDao shoppingOrderDao;
	
	public void addShoppingOrder(ShoppingOrder order) {
		shoppingOrderDao.addShoppingOrder(order);
	}
	
	public void removeShoppingOrder(int orderId) {
		shoppingOrderDao.removeShoppingOrder(orderId);
	}
	
	public void updateShoppingOrder(ShoppingOrder order) {
		shoppingOrderDao.updateShoppingOrder(order);
	}
	
	public ShoppingOrder getShoppingOrderById(int orderId) {
		return shoppingOrderDao.getShoppingOrderById(orderId);
	}
	
	public List<ShoppingOrder> getAllShoppingOrders(int userId) {
		return shoppingOrderDao.getAllShoppingOrders(userId);
	}

	public List<ShoppingOrder> getAllShoppingOrdersByTime(Date startDate, Date endDate) {
		return shoppingOrderDao.getAllShoppingOrdersByTime(startDate, endDate);
	}
	
	public List<ShoppingOrder> getAllShoppingOrdersByTime(Date startDate, Date endDate, int userId) {
		return shoppingOrderDao.getAllShoppingOrdersByTime(startDate, endDate, userId);
	}
	
	public List<ShoppingOrder> getOrdersByItemName(String itemName, User user) {
		return shoppingOrderDao.getOrdersByItemName(itemName, user);
	}
	
	public boolean consistOrder(String orderNumber) {
		return shoppingOrderDao.consistOrder(orderNumber);
	}
}