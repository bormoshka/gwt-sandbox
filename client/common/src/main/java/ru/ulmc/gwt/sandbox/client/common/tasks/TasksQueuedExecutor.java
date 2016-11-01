package ru.ulmc.gwt.sandbox.client.common.tasks;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TasksQueuedExecutor implements Listener {
    protected Set<Queue<QueuedExecutable>> taskQueues = new HashSet<>();

    public TasksQueuedBuilder getBuilder() {
        return new TasksQueuedBuilder(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onException(Throwable throwable) {

    }

    @Override
    public void onFinish() {

    }

    protected void execute(Queue<QueuedExecutable> queue) {
        taskQueues.add(queue);
    }

    public static class TasksQueuedBuilder {
        private final Queue<QueuedExecutable> queue = new LinkedList<>();
        private final TasksQueuedExecutor executor;

        public TasksQueuedBuilder(TasksQueuedExecutor executor) {
            this.executor = executor;
        }

        public TasksQueuedBuilder add(QueuedExecutable executable) {
            QueuedExecutable last = queue.peek();
            if (last != null) {
                last.next(executable);
            }
            queue.add(executable);
            return this;
        }

        public void finish() {
            if (queue.size() > 1) {
                add(new QueuedExecutable<Void>() {
                    @Override
                    public void execute(Listener listener) {
                        listener.onFinish();
                    }

                    @Override
                    public void execute(Listener listener, Void result) {
                        listener.onFinish();
                    }

                    @Override
                    public void next(QueuedExecutable next) {

                    }
                });
                this.executor.execute(queue);
            }
        }

    }

}
