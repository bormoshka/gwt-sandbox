package ru.ulmc.gwt.sandbox.client.common.tasks;

/**
 * Created by 45 on 01.11.2016.
 */
public interface ChainTask<T, R> {
    void execute(T previousOutput, ResultListener<R> listener);
}
