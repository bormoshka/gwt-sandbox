package ru.ulmc.gwt.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.menu.*;
import ru.ulmc.gwt.sandbox.client.common.tasks.*;
import ru.ulmc.gwt.sandbox.client.common.utils.DebugUtil;
import ru.ulmc.gwt.sandbox.client.panel.HasPageContent;
import ru.ulmc.gwt.sandbox.client.panel.main.CitiesPanel;
import ru.ulmc.gwt.sandbox.client.panel.tasks.queue.TasksChainsTestWindow;
import ru.ulmc.gwt.sandbox.shared.api.SimpleServiceAsync;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Entry Point GXT realisation
 */
public class SimpleEntryPointGXT implements EntryPoint {

    Map<String, HasPageContent> contents = new HashMap<>();

    public SimpleEntryPointGXT() {
    }

    public void onModuleLoad() {
        setupQueueWorker();
        final TextField textField = new TextField();
        // textField.setWidth(200);
        TextButton button = new TextButton("Send request!");
        //button.setWidth(200);

        button.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                final SimpleBean bean = new SimpleBean("Groove", 1337L);
                new Task() {
                    @Override
                    public void execute(Listener listener) {
                        SimpleServiceAsync.client.waitForMe(new BaseCallback<String>(listener) {
                            @Override
                            public void onFailure(Throwable throwable) {
                                new AlertMessageBox("Error", throwable.getMessage()).show();
                            }

                            @Override
                            public void onSuccess(String s) {
                                new MessageBox("onSuccess", "Hooray! It's alive!").show();
                            }
                        });
                    }
                }.start();
            }
        });

        VBoxLayoutContainer vblc = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCHMAX);
        vblc.add(textField);
        vblc.add(button);

        CenterLayoutContainer clc = new CenterLayoutContainer();
        clc.add(vblc);

        Window window = new Window();
        window.setHeading("Sending Request2");
        window.setWidth(300);
        window.setHeight(150);
        window.add(clc);
        window.show();

        final NorthSouthContainer northSouthContainer = new NorthSouthContainer();
        northSouthContainer.setNorthWidget(getMenu());
        northSouthContainer.setSouthWidget(new CitiesPanel());

        ContentPanel cp = new ContentPanel();
        cp.setHeading("SimpleEntryPointGXT root ContentPanel");
        cp.add(northSouthContainer);
        cp.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent resizeEvent) {
                northSouthContainer.getSouthWidget().fireEvent(resizeEvent);
            }
        });

        RootPanel.get().add(cp);
    }

    protected MenuBar getMenu() {
        SelectionHandler handler = new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> selectionEvent) {
                switch(selectionEvent.getSelectedItem().getId()) {
                    case "TasksChainsTestWindow": {
                        (new TasksChainsTestWindow()).show();
                        break;
                    }
                }
            }
        };
        MenuItem tctw = new MenuItem("Поочередные вызовы");
        tctw.setId("TasksChainsTestWindow");
        Menu menu2 = new Menu();
        menu2.add(tctw);
        menu2.addSelectionHandler(handler);
        MenuBarItem queueTaskWindow = new MenuBarItem("Тестовые окна");
        queueTaskWindow.setMenu(menu2);


        Menu menu = new Menu();
        menu.add(new MenuBarItem("Население городов"));

        MenuBar menuBar = new MenuBar();
        menuBar.add(new MenuBarItem("Главная", menu));
        menuBar.add(queueTaskWindow);
        return menuBar;
    }

    protected void initContent() {
        contents.put("cities", new CitiesPanel());

    }

    protected void setupQueueWorker() {
        TasksExecutor.set(new TasksExecutor() {
            private AutoProgressMessageBox progressWindow;

            {
                progressWindow = new AutoProgressMessageBox("Loading...");
                progressWindow.setProgressText("Fetching data...");
                progressWindow.addShowHandler(new ShowEvent.ShowHandler() {
                    @Override
                    public void onShow(ShowEvent showEvent) {
                        progressWindow.center();
                    }
                });
            }

            @Override
            public void onStart() {
                progressWindow.show();
                progressWindow.auto();
            }

            @Override
            public void onFinish() {
                progressWindow.hide();
            }

            @Override
            public void onException(Throwable throwable) {
                DebugUtil.log(throwable.getMessage());
            }
        });

        ChainedTasksExecutor.init(new Listener() {
            private AutoProgressMessageBox progressWindow;

            {
                progressWindow = new AutoProgressMessageBox("ChainedTasksExecutor Loading...");
                progressWindow.setProgressText("ChainedTasksExecutor Fetching data...");
                progressWindow.addShowHandler(new ShowEvent.ShowHandler() {
                    @Override
                    public void onShow(ShowEvent showEvent) {
                        progressWindow.center();
                    }
                });
            }

            @Override
            public void onStart() {
                progressWindow.center();
                progressWindow.show();
                progressWindow.auto();
            }

            @Override
            public void onFinish() {
                progressWindow.hide();
            }

            @Override
            public void onException(Throwable throwable) {
                DebugUtil.log(throwable.getMessage());
                progressWindow.hide();
            }
        });
    }
}