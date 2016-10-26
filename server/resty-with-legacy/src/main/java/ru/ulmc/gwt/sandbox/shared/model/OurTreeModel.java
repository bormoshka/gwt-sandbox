package ru.ulmc.gwt.sandbox.shared.model;

import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.ChangeEventSource;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class OurTreeModel extends BaseModelWrapper implements TreeModel {

    protected OurTreeModel parent;

    protected List<OurTreeModel> children;

    public OurTreeModel() {
        children = new ArrayList<>();
    }

    public OurTreeModel(OurTreeModel parent) {
        this();
        parent.add(this);
    }

    @Override
    public void add(ModelData child) {
        insert(child, getChildCount());
    }

    @Override
    public ModelData getChild(int index) {
        if ((index < 0) || (index >= children.size())) return null;
        return (ModelData) children.get(index);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public List<ModelData> getChildren() {
        List<ModelData> hackList = new ArrayList<>();
        for(ModelData md : children) {
            hackList.add(md);
        }
        return hackList;
    }

    @Override
    public OurTreeModel getParent() {
        return parent;
    }

    @Override
    public int indexOf(ModelData child) {
        return children.indexOf(child);
    }

    @Override
    public void insert(ModelData child, int index) {
        adopt(child);
        children.add(index, (OurTreeModel) child);
        ChangeEvent evt = new ChangeEvent(Add, this);
        evt.setParent(this);
        evt.setItem(child);
        evt.setIndex(index);
        notify(evt);
    }

    @Override
    public boolean isLeaf() {
        return children.size() == 0;
    }

    @Override
    public void remove(ModelData child) {
        orphan(child);
        children.remove(child);
        ChangeEvent evt = new ChangeEvent(Remove, this);
        evt.setParent(this);
        evt.setItem(child);
        notify(evt);
    }

    @Override
    public void removeAll() {
        for (int i = children.size() - 1; i >= 0; i--) {
            remove(getChild(i));
        }
    }

    @Override
    public void setParent(TreeModel parent) {
        this.parent = (OurTreeModel) parent;
    }

    public void setParent(OurTreeModel parent) {
        this.parent = parent;
    }

    @Override
    public void notify(ChangeEvent event) {
        super.notify(event);
        if (!isSilent() && parent != null) {
            event.setSource(parent);
            ((ChangeEventSource) parent).notify(event);
        }
    }

    private void setParentInternal(ModelData child) {
        if (child instanceof TreeModel) {
            TreeModel treeChild = (TreeModel) child;
            treeChild.setParent(this);
        } else {
            child.set("gxt-parent", child);
        }
    }

    private TreeModel getParentInternal(ModelData child) {
        if (child instanceof TreeModel) {
            TreeModel treeChild = (TreeModel) child;
            return treeChild.getParent();
        } else {
            return (TreeModel) child.get("gxt-parent");
        }
    }

    private void adopt(ModelData child) {
        TreeModel p = getParentInternal(child);
        if (p != null && p != this) {
            p.remove(child);
        }
        setParentInternal(child);
    }

    private void orphan(ModelData child) {
        if (child instanceof TreeModel) {
            TreeModel treeChild = (TreeModel) child;
            treeChild.setParent(null);
        } else {
            child.remove("gxt-parent");
        }
    }
}
