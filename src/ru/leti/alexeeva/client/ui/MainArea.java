package ru.leti.alexeeva.client.ui;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.IButton;
import ru.leti.alexeeva.client.LibraryServiceAsync;
import ru.leti.alexeeva.shared.Author;
import ru.leti.alexeeva.shared.AuthorLGRecord;
import ru.leti.alexeeva.shared.Book;
import ru.leti.alexeeva.shared.BookLGRecord;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class MainArea  extends VLayout {
    final TabSet topTabSet = new TabSet();

    private LibraryServiceAsync libraryService;
    private ComboBoxItem authorName;
    private ListGrid authorGrid;
    private ListGrid bookGrid;

    public MainArea(LibraryServiceAsync libraryService) {

        super();

        this.libraryService = libraryService;
        this.setOverflow(Overflow.HIDDEN);

        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setTabBarAlign(Side.LEFT);

        VLayout authorTableLayout = new VLayout();
        authorTableLayout.addMember(getAuthorMenu());
        authorGrid = getAuthorGrid();
        authorTableLayout.addMember(authorGrid);

        IButton button = new IButton("Remove");
        button.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                authorGrid.removeSelectedData();
                AuthorLGRecord author = (AuthorLGRecord) authorGrid.getSelectedRecord();
                author.getId();

                //TODO: delete using hibernate
            }
        });

        authorTableLayout.addMember(button);


        VLayout bookTableLayout = new VLayout();
        bookTableLayout.addMember(getBookMenu());
        bookGrid = getBookGrid();
        bookTableLayout.addMember(bookGrid);

        Canvas addAuthorLayout = getAddAuthorLayout();
        Canvas addBookLayout = getAddBookLayout();


        addTabToTopTabset("Authors", authorTableLayout, true);
        addTabToTopTabset("Books", bookTableLayout, true);
   //     addTabToTopTabset("Add author", addAuthorLayout, true);
   //     addTabToTopTabset("Add book", addBookLayout, true);

        this.addMember(topTabSet);
    }

    private Canvas getAddAuthorLayout(){
        final DynamicForm form = new DynamicForm();
        form.setWidth(250);

        final TextItem name = new TextItem();
        name.setName("authorName");
        name.setTitle("Author name");
        name.setRequired(true);

        final DateItem birthYear = new DateItem();
        birthYear.setName("birthYear");
        birthYear.setTitle("Birth year");
        birthYear.setRequired(true);

        final ButtonItem submit = new ButtonItem();
        submit.setName("submit");
        submit.setTitle("Submit");
        submit.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Date date = (Date) birthYear.getValue();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                Author author = new Author(name.getValue().toString(), sqlDate);

                libraryService.saveAuthor(author, new AsyncCallback<Long>() {
                    public void onFailure(Throwable caught) {
                        Window.alert("fail.");
                    }
                    public void onSuccess(Long result) {
                        Window.alert("Author saved.");
                    }
                });
            }
        });

        form.setFields(name, birthYear, submit);

        return form;
    }

    private Canvas getAddBookLayout(){
        final DynamicForm form = new DynamicForm();
        form.setWidth(250);

        final TextItem name = new TextItem();
        name.setName("bookName");
        name.setTitle("Book name");
        name.setRequired(true);

        authorName = new ComboBoxItem();
        authorName.setChangeOnKeypress(false);
        authorName.setName("authorName");
        authorName.setTitle("Author name");
        authorName.setRequired(true);

        final TextItem publisher = new TextItem();
        publisher.setName("publisher");
        publisher.setTitle("Publisher");
        publisher.setRequired(true);

        final DateItem imprintDate = new DateItem();
        imprintDate.setName("imprintDAte");
        imprintDate.setTitle("Imprint date");
        imprintDate.setRequired(true);

        final TextItem keywords = new TextItem();
        keywords.setName("keywords");
        keywords.setTitle("Keywords");

        final IntegerItem price = new IntegerItem();
        price.setName("price");
        price.setTitle("Price");
        price.setRequired(true);

        final IntegerItem quantity = new IntegerItem();
        quantity.setName("quantity");
        quantity.setTitle("Quantity");
        quantity.setRequired(true);

        final DateItem timeOfArrival = new DateItem();
        timeOfArrival.setName("timeOfArrival");
        timeOfArrival.setTitle("Date of arrival");
        timeOfArrival.setRequired(true);

        final ButtonItem submit = new ButtonItem();
        submit.setName("submitBook");
        submit.setTitle("Submit");
        submit.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                Date imprintD = (Date) imprintDate.getValue();
                java.sql.Date sqlImprintD = new java.sql.Date(imprintD.getTime());

                Date timeOfArrivalD = (Date) timeOfArrival.getValue();
                java.sql.Date sqlTimeOfArrivalD = new java.sql.Date(timeOfArrivalD.getTime());

                Book book = new Book(name.getValue().toString(), authorName.getValue().toString(),
                        publisher.getValue().toString(), sqlImprintD, Long.valueOf(price.getValue().toString()),
                        Long.valueOf(quantity.getValue().toString()),  sqlTimeOfArrivalD,
                        keywords.getValue().toString());


                libraryService.saveBook(book, new AsyncCallback<Long>() {
                    public void onFailure(Throwable caught) {
                        Window.alert("fail.");
                    }
                    public void onSuccess(Long result) {
                        Window.alert("Book saved.");
                    }
                });
            }
        });

        form.setFields( name, authorName, publisher, imprintDate, keywords,
                price, quantity, timeOfArrival, submit);

        return form;
    }

    private ListGrid getAuthorGrid(){
        final ListGrid authorGrid = new ListGrid();


        authorGrid.setShowRecordComponents(true);
        authorGrid.setShowRecordComponentsByCell(true);
        authorGrid.setShowAllRecords(true);

        ListGridField idField = new ListGridField("id", "ID");
        idField.setWidth(30);
        idField.setAlign(Alignment.CENTER);

        ListGridField fioField = new ListGridField("fio", "Name");
        fioField.setCanEdit(true);
        ListGridField birthYearField = new ListGridField("birthYear", "Born");
        birthYearField.setCanEdit(true);

        authorGrid.setFields(idField, fioField, birthYearField);
        authorGrid.setCanResizeFields(true);

        fetchAuthorGridData();
        authorGrid.sort();

        authorGrid.setCanEdit(true);
        authorGrid.setCanRemoveRecords(true);

        return authorGrid;
    }

    private void fetchAuthorGridData(){
          libraryService.getAuthors(new AsyncCallback<List<Author>>() {
            public void onFailure(Throwable caught) {
                Window.alert("fail.");
            }
            public void onSuccess(List<Author> result) {

                AuthorLGRecord[] arrayAuthorSmart = new AuthorLGRecord[result.size()];

                for(int i = 0; i<result.size(); ++i){
                    Author author = result.get(i);
                    AuthorLGRecord authorSmart =
                            new AuthorLGRecord(author.getId(), author.getFio(), author.getBirthYear());
                    arrayAuthorSmart[i] = authorSmart;
                }

                authorGrid.setData(arrayAuthorSmart);

                LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

                for(Author author : result){
                      valueMap.put(author.getFio(), author.getFio());
                }
                authorName.setValueMap(valueMap);
            }
        });
    }

    private void fetchBookGridData(){
        libraryService.getBooks(new AsyncCallback<List<Book>>() {
            public void onFailure(Throwable caught) {
                Window.alert("fail.");
            }
            public void onSuccess(List<Book> result) {

                BookLGRecord[] arrayBookSmart = new BookLGRecord[result.size()];

                for(int i = 0; i<result.size(); ++i){
                    Book book = result.get(i);
                    BookLGRecord bookSmart =
                            new BookLGRecord(book.getId(), book.getAuthor(), book.getKeywords(),
                                    book.getName(), book.getPrice(), book.getPublisher(),
                                    book.getQuantity(), book.getTimeOfArrival(), book.getYear());
                    arrayBookSmart[i] = bookSmart;
                }

                bookGrid.setData(arrayBookSmart);
            }
        });
    }

    private ListGrid getBookGrid(){
        final ListGrid bookGrid = new ListGrid();

        bookGrid.setShowRecordComponents(true);
        bookGrid.setShowRecordComponentsByCell(true);
        bookGrid.setShowAllRecords(true);
        bookGrid.setCanResizeFields(true);

        ListGridField idField = new ListGridField("id", "ID");
        idField.setWidth(30);
        idField.setAlign(Alignment.CENTER);

        ListGridField authorField = new ListGridField("author", "Author");
        ListGridField keywordsField = new ListGridField("keywords", "Keywords");
        ListGridField nameField = new ListGridField("name", "Name");
        ListGridField priceField = new ListGridField("price", "Price");
        ListGridField publisherField = new ListGridField("publisher", "Publisher");
        ListGridField quantityField = new ListGridField("quantity", "Quantity");
        ListGridField timeOfArrivalField = new ListGridField("timeOfArrival", "Time of arrival");
        ListGridField yearField = new ListGridField("year", "Year");


        bookGrid.setFields(idField, nameField, authorField, publisherField, yearField,
                keywordsField, priceField, quantityField, timeOfArrivalField);

        fetchBookGridData();
        bookGrid.sort();

        bookGrid.setCanEdit(true);

        return bookGrid;
    }

    private Widget getAuthorMenu(){
        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth100();

        ToolStripButton updateButton = new ToolStripButton();
        updateButton.setTitle("Update");
        updateButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event){
                 fetchAuthorGridData();
            }
        });

        toolStrip.addButton(updateButton);

        ToolStripButton addButton = new ToolStripButton();
        addButton.setTitle("Add");
        addButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event){
                addTabToTopTabset("Add", getAddAuthorLayout(), true);
            }
        });

        toolStrip.addButton(addButton);

        ToolStripButton reportButton = new ToolStripButton();
        reportButton.setTitle("Get report");
        toolStrip.addButton(reportButton);

        return toolStrip;
    }

    private Widget getBookMenu(){
        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth100();

        ToolStripButton updateButton = new ToolStripButton();
        updateButton.setTitle("Update");
        updateButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event){
                 fetchBookGridData();
            }
        });

        toolStrip.addButton(updateButton);

        ToolStripButton addButton = new ToolStripButton();
        addButton.setTitle("Add");
        addButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event){
                 addTabToTopTabset("Add", getAddBookLayout(), true);
            }
        });
        toolStrip.addButton(addButton);

        ToolStripButton reportButton = new ToolStripButton();
        reportButton.setTitle("Get report");
        reportButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event){
                //getBookReport();
            }
        });
        toolStrip.addButton(reportButton);

        return toolStrip;
    }

    private void addTabToTopTabset(String title, Canvas pane, boolean closable) {
        Tab tab = createTab(title, pane, closable);
        topTabSet.addTab(tab);
        topTabSet.selectTab(tab);
    }

    private Tab createTab(String title, Canvas pane, boolean closable) {
        Tab tab = new Tab(title);
        tab.setCanClose(closable);
        tab.setPane(pane);
        return tab;
    }

}
