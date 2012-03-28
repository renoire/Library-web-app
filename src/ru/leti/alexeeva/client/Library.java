package ru.leti.alexeeva.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.smartgwt.client.widgets.layout.VLayout;
import ru.leti.alexeeva.client.ui.HeaderArea;
import ru.leti.alexeeva.client.ui.MainArea;


public class Library implements EntryPoint {
    private VLayout mainLayout;

	private final LibraryServiceAsync libraryService = GWT
			.create(LibraryService.class);

	public void onModuleLoad() {
        Window.enableScrolling(false);
        Window.setMargin("0px");

        mainLayout = new VLayout();
        mainLayout.setWidth100();
        mainLayout.setHeight100();

        mainLayout.addMember(new HeaderArea());
        mainLayout.addMember(new MainArea(libraryService));

        RootLayoutPanel.get().add(mainLayout);

        //Jasper

       /* String textToServer = "sdfsdf";
        libraryService.getAuthorReport(textToServer, new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                Window.alert("fail.");
            }

            public void onSuccess(String result) {
                Window.alert(result);
            }
        });   */
	}
}
