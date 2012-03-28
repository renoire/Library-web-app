package ru.leti.alexeeva.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.leti.alexeeva.shared.Author;
import ru.leti.alexeeva.shared.Book;

import java.util.List;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface LibraryServiceAsync {
	void getAuthors(AsyncCallback<List<Author>> callback);
	void getBooks(AsyncCallback<List<Book>> callback);
	void saveAuthor(Author author, AsyncCallback<Long> callback);
	void saveBook(Book book, AsyncCallback<Long> callback);
    void getAuthorReport(String input, AsyncCallback<String> callback);
}
