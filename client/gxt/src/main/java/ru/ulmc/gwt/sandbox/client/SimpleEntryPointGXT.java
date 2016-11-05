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
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import ru.ulmc.gwt.sandbox.client.common.tasks.*;
import ru.ulmc.gwt.sandbox.client.common.utils.DebugUtil;
import ru.ulmc.gwt.sandbox.client.panel.HasPageContent;
import ru.ulmc.gwt.sandbox.client.panel.main.CitiesPanel;
import ru.ulmc.gwt.sandbox.shared.api.SimpleServiceAsync;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Entry Point GXT realisation
 */
public class SimpleEntryPointGXT implements EntryPoint {
    final NorthSouthContainer northSouthContainer = new NorthSouthContainer();

    Map<String, HasPageContent> contents = new HashMap<>();

    public SimpleEntryPointGXT() {
        setupQueueWorker();
    }

    public void onModuleLoad() {
        initContent();

        northSouthContainer.setNorthWidget(new MainMenu(this));
        northSouthContainer.setSouthWidget(contents.get("cities").getContentHolder());

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

    public void changeContent(String newContentID) {
        northSouthContainer.setSouthWidget(contents.get(newContentID).getContentHolder());
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
                        progressWindow.restore();
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
                progressWindow.hide();
            }
        });
    }
}