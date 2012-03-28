package ru.leti.alexeeva.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import ru.leti.alexeeva.JasperReporter;
import ru.leti.alexeeva.client.LibraryService;
import ru.leti.alexeeva.shared.Author;
import ru.leti.alexeeva.shared.Book;

import java.util.ArrayList;
import java.util.List;



/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LibraryServiceImpl extends RemoteServiceServlet implements
		LibraryService {

    private static SessionFactory sessionFactory;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        final ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

	public List<Author> getAuthors(){
        List<Author> authors;
        configureSessionFactory();
        Session session =  sessionFactory.openSession();
        session.beginTransaction();
        authors = new ArrayList<Author>(session.createQuery("from Author").list());
        session.getTransaction().commit();
        return authors;
	}
	
	public List<Book> getBooks(){
		List<Book> books;
        configureSessionFactory();
        Session session =  sessionFactory.openSession();
        session.beginTransaction();
        books = new ArrayList<Book>(session.createQuery("from Book").list());
        session.getTransaction().commit();
        return books;
	}

	public Long saveAuthor(Author author){
		configureSessionFactory();
        Session session =  sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        return author.getId();
	}
	
	public Long saveBook(Book book){
		configureSessionFactory();
        Session session =  sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        return book.getId();
	}

    public String getAuthorReport(String input){

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script vulnerabilities.
        input = escapeHtml(input);
        userAgent = escapeHtml(userAgent);

          try {
              new JasperReporter().doReport(input);
          } catch (JRException e) {
              System.out.println(e);
              e.printStackTrace(System.out);
          }

        return "blahblah";

    }
    private String escapeHtml(String html) {
        if (html == null) {
          return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
            ">", "&gt;");
    }
}
