package eOrderButler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eOrderButler.model.Item;
import eOrderButler.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping(value = "/order/getItemById/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public Item getItemById(@PathVariable(value = "itemId") int itemId) {
		Item item = itemService.getItemById(itemId);
		return item;
	}
}
