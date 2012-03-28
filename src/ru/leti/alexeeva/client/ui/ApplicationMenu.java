package ru.leti.alexeeva.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class ApplicationMenu extends HLayout {
    private static final int APPLICATION_MENU_HEIGHT = 27;

    private Label label;

    public ApplicationMenu() {

        super();
        this.setHeight(APPLICATION_MENU_HEIGHT);

        label = new Label();
        label.setContents("Application Menu");
        label.setAlign(Alignment.CENTER);
        label.setOverflow(Overflow.HIDDEN);

        this.addMember(label);


        /* menu here
        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth100();

        ToolStripButton iconButton = new ToolStripButton();
        iconButton.setTitle("MyButton");
        toolStrip.addButton(iconButton);

        MenuItem[] itemArray = new MenuItem[4];

        itemArray[0] = new MenuItem("MenuItem1");
        Menu menu1 = new Menu();
        menu1.setData(new MenuItem("SubMenuItem11"), new MenuItem("SubMenuItem12"));
        itemArray[0].setSubmenu(menu1);

        itemArray[1] = new MenuItem("MenuItem2");
        Menu menu2 = new Menu();
        menu2.setData(new MenuItem("SubMenuItem21"), new MenuItem("SubMenuItem22"));
        itemArray[1].setSubmenu(menu2);

        Menu parentMenu = new Menu();
        parentMenu.setCanSelectParentItems(true);
        parentMenu.setData(itemArray);

        ToolStripMenuButton menuButton =
             new ToolStripMenuButton("Menu", parentMenu);
        toolStrip.addMenuButton(menuButton);
        hlayout.addMember(toolStrip);

        */

    }

}
