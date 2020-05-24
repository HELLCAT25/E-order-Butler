package eOrderButler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eOrderButler.dao.ItemDao;
import eOrderButler.model.Item;


@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	
	public void addItem(Item item) {
		itemDao.addItem(item);
	}
	
	public void removeItem(int itemId) {
		itemDao.removeItem(itemId);
	}
	
	public void updateItem(Item item) {
		itemDao.updateItem(item);
	}
	
	public Item getItemById(int itemId) {
		return itemDao.getItemById(itemId);
	}
}
