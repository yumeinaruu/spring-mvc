package mvc.com.repository;

import mvc.com.model.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PageRepository {

    public SessionFactory sessionFactory;

    public PageRepository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public ArrayList<Page> getAllPages(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Page> query = session.createQuery("from pages", Page.class);
        ArrayList<Page> list = (ArrayList<Page>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Page getPageById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Page page = session.get(Page.class,id);
        session.getTransaction().commit();
        session.close();
        return page;
    }

    public void createPage(Page page){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(page);
        session.getTransaction().commit();
        session.close();
    }

    public void updatePage(Page page){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(page);
        session.getTransaction().commit();
        session.close();
    }

    public void deletePage(Page page){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(page);
        session.getTransaction().commit();
        session.close();
    }
}