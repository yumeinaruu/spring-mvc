package mvc.com.repository;


import mvc.com.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthorRepository {

    public SessionFactory sessionFactory;

    public AuthorRepository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public ArrayList<Author> getAllAuthors(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Author> query = session.createQuery("from author", Author.class);
        ArrayList<Author> list = (ArrayList<Author>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Author getAuthorById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Author author = session.get(Author.class,id);
        session.getTransaction().commit();
        session.close();
        return author;
    }

    public void createAuthor(Author author){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
    }

    public void updateAuthor(Author author){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(author);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAuthor(Author author){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(author);
        session.getTransaction().commit();
        session.close();
    }
}