package ru.ulmc.gwt.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import com.extjs.gxt.themes.client.Slate;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.ThemeManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;

import java.util.ArrayList;
import java.util.List;

import ru.ulmc.gwt.sandbox.client.common.tasks.SimpleCallback;
import ru.ulmc.gwt.sandbox.client.common.utils.DebugUtil;
import ru.ulmc.gwt.sandbox.shared.api.SimpleServiceAsync;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBaseModelBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleTree;

/**
 * Entry Point GXT2 realization
 */
public class SimpleEntryPointLegacyGXT implements EntryPoint {

    protected TreeGrid<SimpleTree> treeGrid = null;

    public SimpleEntryPointLegacyGXT() {

    }

    public void onModuleLoad() {

        ThemeManager.register(Slate.BLUE);
        GXT.setDefaultTheme(Slate.BLUE, true);

        final TextArea area = new TextArea();
        area.setWidth(300);
        area.setHeight(250);

        Button btn = new Button("GET BEAN");
        btn.setWidth(150);
        btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent buttonEvent) {
                SimpleServiceAsync.client.hello(new SimpleCallback<SimpleBaseModelBean>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                        area.setValue(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(SimpleBaseModelBean s) {
                        area.setValue(s.getA() + " " + s.getB());
                        DebugUtil.log(s);
                        DebugUtil.log(s.FIELD_A + " " + s.FIELD_B + " " + s.get(s.FIELD_A));
                    }
                });
            }
        });

        Button btn2 = new Button("POST BEAN");
        btn2.setWidth(150);
        btn2.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent buttonEvent) {
                SimpleBaseModelBean bean = new SimpleBaseModelBean("No", "WAY!");
                bean.setTestField("Yep! Оно здесь!");
                bean.set("UNMAPPED_FIELD", 1337L);
                SimpleServiceAsync.client.post(bean, new SimpleCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                        area.setValue(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void s) {
                        area.setValue("onSuccess");
                        DebugUtil.log(s);
                    }
                });
            }
        });

        CenterLayout cl = new CenterLayout();

        ContentPanel contentPanel = new ContentPanel();
        contentPanel.setTitle("Legacy GXT testing");
        contentPanel.setLayout(cl);
        contentPanel.setHeight(500);

        contentPanel.addButton(btn);
        contentPanel.addButton(btn2);
        contentPanel.add(area);
        setupTreeWindow();
        RootPanel.get().add(contentPanel);
    }

    protected void setupTreeWindow() {
        List<ColumnConfig> columnConfigList = new ArrayList<>();
        columnConfigList.add(new ColumnConfig(SimpleTree.FIELD_NAME, "Name", 120));

        TreeStore<SimpleTree> treeStore = new TreeStore<>(new BaseTreeLoader<SimpleTree>(new DataProxy<List<SimpleTree>>() {
            @Override
            public void load(DataReader<List<SimpleTree>> dataReader, Object o, final AsyncCallback<List<SimpleTree>> asyncCallback) {
                SimpleServiceAsync.client.getTree(new SimpleCallback<List<SimpleTree>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        DebugUtil.log(throwable);
                        asyncCallback.onFailure(throwable);
                    }

                    @Override
                    public void onSuccess(List<SimpleTree> simpleTrees) {
                        treeGrid.getTreeStore().removeAll();
                        treeGrid.getTreeStore().add(simpleTrees, true);
                        asyncCallback.onSuccess(simpleTrees);
                    }
                });
            }
        }));

        treeGrid = new TreeGrid<>(treeStore, new ColumnModel(columnConfigList));

        Button loadBtn = new Button("Load");
        loadBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent buttonEvent) {
                DebugUtil.log("FireGridLoading");
                treeGrid.getTreeStore().getLoader().load();
            }
        });
        Window win = new Window();
        win.setHeadingText("SimpleTree Test");
        win.add(treeGrid);
        win.setHeight(300);
        win.setWidth(200);
        win.addButton(loadBtn);
        win.show();
    }
}