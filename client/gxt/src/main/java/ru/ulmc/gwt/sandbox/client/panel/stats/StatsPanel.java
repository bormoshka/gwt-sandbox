package ru.ulmc.gwt.sandbox.client.panel.stats;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import ru.ulmc.gwt.sandbox.client.panel.HasPageContent;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;
import ru.ulmc.gwt.sandbox.shared.model.actuator.HealthModel;

public class StatsPanel extends ContentPanel implements HasPageContent {

    private static final CityModel.CityProperties properties = GWT.create(CityModel.CityProperties.class);
    private Grid<HealthModel> healthGrid = null;

    public StatsPanel() {
        setHeaderVisible(false);

    }

    private ColumnModel<CityModel> initColumnModel() {


        return new ColumnModel<>(null);
    }

    private ListStore<CityModel> initListStore() {

        return new ListStore<>(properties.key());
    }

    @Override
    public Widget getContentHolder() {
        return this;
    }
}
