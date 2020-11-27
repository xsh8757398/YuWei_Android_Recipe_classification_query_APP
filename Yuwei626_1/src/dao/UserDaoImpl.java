package dao;

import org.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import user.User;

public class UserDaoImpl implements UserDao {
	public void save(User user){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = (Transaction) session.beginTransaction();
		try {
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
//			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void update(User user) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = (Transaction) session.beginTransaction();
		try {
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
//			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public User findById(int id) {
		User user = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = (Transaction) session.beginTransaction();
		try {
			user = (User) session.get(User.class, id);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
//			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return user;
	}

	@Override
	public void delete(User user) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = (Transaction) session.beginTransaction();
		try {
			session.delete(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
//			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

}
