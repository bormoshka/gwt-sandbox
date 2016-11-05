package ru.ulmc.gwt.sandbox.client.panel.main;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.event.CellClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import ru.ulmc.gwt.sandbox.client.common.LoaderCallback;
import ru.ulmc.gwt.sandbox.client.common.tasks.Listener;
import ru.ulmc.gwt.sandbox.client.common.tasks.Task;
import ru.ulmc.gwt.sandbox.client.common.utils.DebugUtil;
import ru.ulmc.gwt.sandbox.client.panel.HasPageContent;
import ru.ulmc.gwt.sandbox.shared.api.SimpleServiceAsync;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;

import java.util.ArrayList;
import java.util.List;

public class CitiesPanel extends ContentPanel implements HasPageContent {

    private static final CityModel.CityProperties properties = GWT.create(CityModel.CityProperties.class);
    private Grid<CityModel> grid = null;

    public CitiesPanel() {
        setHeaderVisible(false);
        grid = new Grid<>(initListStore(), initColumnModel());
        grid.setLoader(new ListLoader<>(new DataProxy<ListLoadConfig, ListLoadResult>() {
            @Override
            public void load(ListLoadConfig loadConfig, Callback<ListLoadResult, Throwable> callback) {
                new Task() {
                    @Override
                    public void execute(Listener listener) {
                        SimpleServiceAsync.client.getCities(new LoaderCallback<>(listener, grid.getStore()));
                    }
                }.start();
            }
        }));
        grid.getView().setAutoFill(true);
        grid.getView().setStripeRows(true);
        grid.getView().setAdjustForHScroll(true);
        grid.getLoader().load();
        grid.addCellClickHandler(new CellClickEvent.CellClickHandler() {
            @Override
            public void onCellClick(CellClickEvent event) {
                DebugUtil.log("onCellClick: " + event.getCellIndex() + ", " + event.getRowIndex());
            }
        });
        grid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                DebugUtil.log("onRowClick: " + event.getColumnIndex() + ", " + event.getRowIndex());
            }
        });
        setWidget(grid);
    }

    private ColumnModel<CityModel> initColumnModel() {
        ColumnConfig<CityModel, String> name = new ColumnConfig<>(properties.name(), 50, "Название");
        ColumnConfig<CityModel, String> country = new ColumnConfig<>(properties.country(), 75, "Страна");
        ColumnConfig<CityModel, Float> size = new ColumnConfig<>(properties.size(), 75, "Площадь");
        ColumnConfig<CityModel, Long> population = new ColumnConfig<>(properties.population(), 75, "Население");
        ColumnConfig<CityModel, Long> density = new ColumnConfig<>(properties.density(), 75, "Плотность населения");

        List<ColumnConfig<CityModel, ?>> columns = new ArrayList<>();
        columns.add(name);
        columns.add(country);
        columns.add(size);
        columns.add(population);
        columns.add(density);

        return new ColumnModel<>(columns);
    }

    private ListStore<CityModel> initListStore() {

        return new ListStore<>(properties.key());
    }

    @Override
    public Widget getContentHolder() {
        return this;
    }
}
