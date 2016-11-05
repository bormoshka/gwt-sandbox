package ru.ulmc.gwt.sandbox.client;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.menu.*;
import ru.ulmc.gwt.sandbox.client.panel.tasks.queue.TasksChainsTestWindow;
import ru.ulmc.gwt.sandbox.client.panel.tasks.request.SimpleRequestWindow;

public class MainMenu extends MenuBar {

    public static final String TASKS_CHAINS = "tctw";
    public static final String SIMPLE_REQUEST_WINDOW = "sr";
    public static final String PANEL_CITIES = "cities";
    private SimpleEntryPointGXT parent;

    public MainMenu(SimpleEntryPointGXT parent) {
        this.parent = parent;
        add(getMainMenu());
        add(getWindowMenu());
    }

    protected MenuBarItem getMainMenu() {
        Menu menu = new Menu();
        menu.add(new MenuBarItem("Население городов"));
        menu.addSelectionHandler(new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> selectionEvent) {
                parent.changeContent(selectionEvent.getSelectedItem().getId());
            }
        });

        return new MenuBarItem("Главная", menu);
    }

    protected MenuBarItem getWindowMenu() {
        MenuItem tctw = new MenuItem("Поочередные вызовы");
        tctw.setId(TASKS_CHAINS);
        MenuItem sr = new MenuItem("Простой запрос");
        sr.setId(SIMPLE_REQUEST_WINDOW);


        Menu menu = new Menu();
        menu.add(tctw);
        menu.add(sr);
        menu.addSelectionHandler(getWindowMenuHandler());

        return new MenuBarItem("Тестовые окна", menu);
    }

    protected SelectionHandler<Item> getWindowMenuHandler() {
        return new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> selectionEvent) {
                switch (selectionEvent.getSelectedItem().getId()) {
                    case TASKS_CHAINS: {
                        (new TasksChainsTestWindow()).show();
                        break;
                    }
                    case SIMPLE_REQUEST_WINDOW: {
                        new SimpleRequestWindow();
                        break;
                    }
                }
            }
        };
    }
}
