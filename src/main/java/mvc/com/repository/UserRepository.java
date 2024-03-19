package mvc.com.repository;

import mvc.com.model.Book;
import mvc.com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    private Session session = null;

    public UserRepository() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }

    public List<User> findAll() {
        Query<User> query = session.createQuery("FROM users", User.class);
        return query.getResultList();
    }

    public User findById(Long id) {
        try {
            return session.get(User.class, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean createUser(User user) {
        try {
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean updateUser(User user) {
        try {
            session.getTransaction().begin();
            session.merge(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean updateUserPassword(String password, Long id) {
        try {
            User user = session.get(User.class, id);
            user.setUserPassword(password);
            session.getTransaction().begin();
            session.merge(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean deleteUser(Long id) {
        try {
            session.getTransaction().begin();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
}
