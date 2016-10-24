package ru.ulmc.gwt.sandbox.shared.model;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class SimpleTree extends BaseTreeModel {

    public final static String FIELD_NAME = "name";
    public final static String FIELD_TREE_ID = "treeId";
    public final static String FIELD_ID = "id";

    public SimpleTree() {
    }

    public SimpleTree(String name, String trId, String id) {
        set(FIELD_NAME, name);
        set(FIELD_TREE_ID, trId);
        set(FIELD_ID, id);
    }

    public SimpleTree(String name, SimpleTree[] children) {
        set(FIELD_NAME, name);

        for (int i = 0; i < children.length; i++) {
            add(children[i]);
        }
    }
}
