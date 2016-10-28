package ru.ulmc.gwt.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import ru.ulmc.gwt.sandbox.client.common.tasks.SimpleCallback;
import ru.ulmc.gwt.sandbox.client.common.tasks.Listener;
import ru.ulmc.gwt.sandbox.client.common.tasks.Task;
import ru.ulmc.gwt.sandbox.client.common.tasks.TaskQueue;
import ru.ulmc.gwt.sandbox.client.common.utils.DebugUtil;
import ru.ulmc.gwt.sandbox.client.panel.PagePanel;
import ru.ulmc.gwt.sandbox.shared.api.SimpleServiceAsync;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBean;

/**
 * Entry Point GXT realisation
 */
public class SimpleEntryPointGXT implements EntryPoint {

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
                        SimpleServiceAsync.client.waitForMe(new SimpleCallback<String>(listener) {
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
        window.setHeading("Sending Request");
        window.setWidth(300);
        window.setHeight(150);
        window.add(clc);
        window.show();

        final NorthSouthContainer northSouthContainer = new NorthSouthContainer();
        northSouthContainer.setNorthWidget(getMenu());
        northSouthContainer.setSouthWidget(new PagePanel());

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
        Menu menu = new Menu();
        menu.add(new MenuBarItem("Example MenuBarItem"));
        menu.add(new MenuBarItem("Example MenuBarItem"));

        MenuBar menuBar = new MenuBar();
        menuBar.add(new MenuBarItem("Root menuBarItem 1", menu));
        return menuBar;
    }

    protected void setupQueueWorker() {
        TaskQueue.set(new TaskQueue() {
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
    }
}