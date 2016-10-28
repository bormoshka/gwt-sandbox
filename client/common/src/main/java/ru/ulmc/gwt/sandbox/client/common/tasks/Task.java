package ru.ulmc.gwt.sandbox.client.common.tasks;

public abstract class Task {

    public abstract void execute(Listener listener);

    public void onCancel() {

    }

    public void start(TaskQueue queue) {
        queue.execute(this);
    }

    public void start() {TaskQueue.get().execute(this);}

}