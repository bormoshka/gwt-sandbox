package ru.ulmc.gwt.sandbox.client.common.tasks;

public abstract class Task implements Executable {

    public abstract void execute(Listener listener);

    public void onCancel() {

    }

    public void start(TasksExecutor queue) {
        queue.execute(this);
    }

    public void start() {
        TasksExecutor.get().execute(this);}

}