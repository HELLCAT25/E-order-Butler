package eOrderButler.dao;

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
}
