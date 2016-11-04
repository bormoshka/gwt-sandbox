package ru.ulmc.gwt.sandbox.client.common.tasks;

import ru.ulmc.gwt.sandbox.client.common.exception.NotInitializedException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ChainedTasksExecutor {
    protected Set<Queue<ChainTask>> taskQueues = new HashSet<>();
    protected static Listener commonListener = null;
    protected static ChainedTasksExecutor instance = new ChainedTasksExecutor();

    protected ChainedTasksExecutor() {
    }

    public static void init(Listener listener) {
        commonListener = listener;
    }

    public static TasksChainBuilder getBuilder() {
        return new TasksChainBuilder(instance);
    }

    protected void prepareExecution(Queue<ChainTask> queue) {
        if (commonListener == null) {
            throw new NotInitializedException("TasksQueuedExecutor wasn't initialized");
        }
        taskQueues.add(queue);
        commonListener.onStart();
        try {
            execute(null, queue);
        } catch( Throwable throwable) {
            commonListener.onException(throwable);
        }
    }

    protected void execute(Object returnedValue, final Queue<ChainTask> queue) {
        ChainTask executable = queue.poll();
        if (executable == null) {
            commonListener.onFinish();
            taskQueues.remove(queue);
            return;
        }
        executable.execute(returnedValue, new ResultListener() {
            @Override
            public void onException(Throwable throwable) {
                onExceptionInner(throwable, queue);
            }

            @Override
            public void onFinish(Object result) {
                execute(result, queue);
            }
        });
    }

    protected void onExceptionInner(Throwable throwable, Queue<ChainTask> queue) {
        commonListener.onException(throwable);
        taskQueues.remove(queue);
    }

    public static class TasksChainBuilder {
        private final Queue<ChainTask> queue = new LinkedList<>();
        private final ChainedTasksExecutor executor;

        public TasksChainBuilder(ChainedTasksExecutor executor) {
            this.executor = executor;
        }

        public TasksChainBuilder add(ChainTask executable) {
            queue.add(executable);
            return this;
        }

        public void start() {
            if (!queue.isEmpty()) {
                this.executor.prepareExecution(queue);
            }
        }
    }

}
