package mvc.com.repository;

import com.tms.books.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class BookRepository {

    public SessionFactory sessionFactory;

    public BookRepository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public ArrayList<Book> getAllBooks(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Book> query = session.createQuery("from books", Book.class);
        ArrayList<Book> list = (ArrayList<Book>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Book getBookById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.get(Book.class,id);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    public void createBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

    public void updateBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(book);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
        session.close();
    }
}