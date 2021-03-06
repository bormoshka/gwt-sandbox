package ru.ulmc.gwt.sandbox.shared.model;

public class SimpleTree extends OurTreeModel {

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
