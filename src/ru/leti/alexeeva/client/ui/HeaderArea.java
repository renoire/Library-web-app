package ru.leti.alexeeva.client.ui;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;


public class HeaderArea extends HLayout {
    private static final int HEADER_AREA_HEIGHT = 10;

    public HeaderArea() {

        super();

        this.setHeight(HEADER_AREA_HEIGHT);

        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth100();

        Menu menuWidgets = new Menu();
        menuWidgets.setData(new MenuItem("Authors list"), new MenuItem("Books list"));

        ToolStripMenuButton menuButton =
             new ToolStripMenuButton("Tabs", menuWidgets);
        toolStrip.addMenuButton(menuButton);

        this.addMember(toolStrip);
    }

}
