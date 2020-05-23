package eOrderButler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eOrderButler.dao.ShoppingOrderDao;
import eOrderButler.model.ShoppingOrder;

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
	
	public List<ShoppingOrder> getAllShoppingOrders() {
		return shoppingOrderDao.getAllShoppingOrders();
	}
}
