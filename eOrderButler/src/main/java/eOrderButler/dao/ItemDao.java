package eOrderButler.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eOrderButler.model.Item;

@Repository
public class ItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItem(Item item) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public void removeItem(int itemId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Item item = (Item) session.get(Item.class, itemId);
			session.delete(item);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public void updateItem(Item item) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(item);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	
	public Item getItemById(int itemId) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Item item = (Item) session.get(Item.class, itemId);
			session.getTransaction().commit();
			return item;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Item> getItemListByShoppingOrderId(int orderId) {
		List<Item> items = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
			Root<Item> root = criteriaQuery.from(Item.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("orderId"), orderId));
			items = session.createQuery(criteriaQuery).getResultList();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	
}
