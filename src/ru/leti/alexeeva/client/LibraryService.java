package ru.leti.alexeeva.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.leti.alexeeva.shared.Author;
import ru.leti.alexeeva.shared.Book;

import java.util.List;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("libraryService")
public interface LibraryService extends RemoteService {
	public List<Author> getAuthors();
	public List<Book> getBooks();
	public Long saveAuthor(Author author);
	public Long saveBook(Book book);
    public String getAuthorReport(String input);
}
