package eOrderButler.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eOrderButler.model.Item;
import eOrderButler.model.ShoppingOrder;
import eOrderButler.model.User;

@Repository
public class ShoppingOrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addShoppingOrder(ShoppingOrder order) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(order);
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
	
	public void removeShoppingOrder(int orderId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			ShoppingOrder order = (ShoppingOrder) session.get(ShoppingOrder.class, orderId);
			session.delete(order);
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
	
	public void updateShoppingOrder(ShoppingOrder order) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(order);
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
	
	public ShoppingOrder getShoppingOrderById(int orderId) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			ShoppingOrder order = (ShoppingOrder) session.get(ShoppingOrder.class, orderId);
			session.getTransaction();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ShoppingOrder> getAllShoppingOrders(int userId) {
		List<ShoppingOrder> shoppingOrders = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ShoppingOrder> criteriaQuery = criteriaBuilder.createQuery(ShoppingOrder.class);
			Root<ShoppingOrder> root = criteriaQuery.from(ShoppingOrder.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), userId));
			shoppingOrders = session.createQuery(criteriaQuery).getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shoppingOrders;
	}
	
	public List<ShoppingOrder> getAllShoppingOrdersByTime(Date startDate, Date endDate) {
		List<ShoppingOrder> shoppingOrders = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ShoppingOrder> criteriaQuery = criteriaBuilder.createQuery(ShoppingOrder.class);
			Root<ShoppingOrder> root = criteriaQuery.from(ShoppingOrder.class);
			criteriaQuery.select(root).where(criteriaBuilder.between(root.get("date"), startDate, endDate));
			shoppingOrders = session.createQuery(criteriaQuery).getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shoppingOrders;
	}
	
	public List<ShoppingOrder> getAllShoppingOrdersByTime(Date startDate, Date endDate, int userId) {
		List<ShoppingOrder> shoppingOrders = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ShoppingOrder> criteriaQuery = criteriaBuilder.createQuery(ShoppingOrder.class);
			Root<ShoppingOrder> root = criteriaQuery.from(ShoppingOrder.class);
			
			Predicate[] predicates = new Predicate[2];
			predicates[0] = criteriaBuilder.equal(root.get("user"), userId);
			predicates[1] = criteriaBuilder.between(root.get("date"), startDate, endDate);
	
			criteriaQuery.select(root).where(predicates);
			shoppingOrders = session.createQuery(criteriaQuery).getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shoppingOrders;
	}
	
	public List<ShoppingOrder> getOrdersByItemName(String itemName, User user) {
		List<ShoppingOrder> shoppingOrderResult = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ShoppingOrder> orderQuery = criteriaBuilder.createQuery(ShoppingOrder.class);
			Root<ShoppingOrder> order = orderQuery.from(ShoppingOrder.class);
			
			Subquery<Item> itemSubQuery = orderQuery.subquery(Item.class);
			Root<Item> subQueryItem = itemSubQuery.from(Item.class);
			
			Subquery<ShoppingOrder> orderSubQuery = itemSubQuery.subquery(ShoppingOrder.class);
			Root<ShoppingOrder> subQueryOrder = orderSubQuery.from(ShoppingOrder.class);
			orderSubQuery.select(subQueryOrder.get("orderId")).where(criteriaBuilder.equal(subQueryOrder.get("user"), user.getUserId()));
			
			
			Predicate[] predicates = new Predicate[2];
			predicates[0] = criteriaBuilder.in(subQueryItem.get("order")).value(orderSubQuery);
			predicates[1] = criteriaBuilder.like(subQueryItem.get("itemName"), "%" + itemName + "%");
			
			itemSubQuery.select(subQueryItem.get("order")).where(predicates);
			
			
			orderQuery.select(order).where(criteriaBuilder.in(order.get("orderId")).value(itemSubQuery));
			
			shoppingOrderResult = session.createQuery(orderQuery).getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shoppingOrderResult;
	}
	
}