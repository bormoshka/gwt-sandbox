package ru.ulmc.gwt.sandbox.client.common.tasks;


import java.util.HashSet;
import java.util.Set;

public class TasksExecutor implements Listener {
    private final Set<Task> taskSet = new HashSet<>();
    private static TasksExecutor main = null;

    public static TasksExecutor get() {
        return main;
    }

    public static void set(TasksExecutor main) {
        TasksExecutor.main = main;
    }

    public final void execute(Task... tasks) {
        for (Task task : tasks) {
            launch(task);
        }
    }

    public final void cancel() {
        for (Task task : taskSet) {
            task.onCancel();
        }
        taskSet.clear();
        onFinish();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onException(Throwable throwable) {

    }

    protected void onExceptionInner(Throwable throwable, Task task) {
        onException(throwable);
        complete(task);
    }

    protected void launch(final Task task) {
        if (!taskSet.add(task)) {
            return; // Задача уже есть, повторная запись в очередь не требуется
        }
        if (taskSet.size() == 1) {
            onStart(); //Инициализирует только первая задача
        }
        try {
            task.execute(new Listener() {
                @Override
                public void onStart() {
                }

                @Override
                public void onException(Throwable throwable) {
                    onExceptionInner(throwable, task);
                }

                @Override
                public void onFinish() {
                    complete(task);
                }
            });
        } catch(Throwable throwable) {
            onException(throwable);
        }
    }

    protected void complete(Task task) {
        taskSet.remove(task);

        if (taskSet.isEmpty()) {
            onFinish(); //последняя задача в цепочке
        }
    }


}
