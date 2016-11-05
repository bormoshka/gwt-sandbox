package ru.ulmc.gwt.sandbox.client.panel.tasks.queue;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextArea;
import ru.ulmc.gwt.sandbox.client.common.ChainedCallback;
import ru.ulmc.gwt.sandbox.client.common.tasks.ChainTask;
import ru.ulmc.gwt.sandbox.client.common.tasks.ChainedTasksExecutor;
import ru.ulmc.gwt.sandbox.client.common.tasks.ResultListener;
import ru.ulmc.gwt.sandbox.shared.api.SimpleServiceAsync;

import java.util.List;

public class TasksChainsTestWindow extends Window {
    private TextArea outputLog;

    public TasksChainsTestWindow() {
        setHeading("Testing TasksQueuedExecutor");
        outputLog = new TextArea();
        outputLog.setHeight(300);
        outputLog.setWidth(500);
        add(outputLog);
        TextButton startBtn = new TextButton("Start!");
        startBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                run(false);
            }
        });
        TextButton startErrBtn = new TextButton("Start with error!");
        startErrBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                run(true);
            }
        });
        addButton(startBtn);
        addButton(startErrBtn);
    }

    protected void append(String msg) {
        outputLog.setValue(outputLog.getCurrentValue() + "\n" + msg);
    }

    public void run(final boolean withError) {
        ChainedTasksExecutor.getBuilder().add(new ChainTask<Object, String>() {
            @Override
            public void execute(Object previousOutput, ResultListener<String> listener) {
                append("Before executing firstTask");
                SimpleServiceAsync.client.stepOne(new ChainedCallback<String>(listener) {
                    @Override
                    public void onSuccess(String t) {
                        append("onSuccess firstTask: " + t);
                        if (t != null) {
                            append("onSuccess firstTask logic true");
                        } else {
                            append("onSuccess firstTask logic false");
                        }
                    }
                });
            }
        }).add(new ChainTask<String, Long>() {
            @Override
            public void execute(String previousOutput, ResultListener<Long> listener) {
                append("Before executing secondTask: " + previousOutput);
                SimpleServiceAsync.client.stepTwo(withError, previousOutput, new ChainedCallback<Long>(listener) {
                    @Override
                    public void onSuccess(Long t) {
                        append("onSuccess secondTask: " + t);
                        if (t != null) {
                            append("onSuccess secondTask logic true");
                        } else {
                            append("onSuccess secondTask logic false");
                        }
                    }
                });
            }
        }).add(new ChainTask<Long, Long>() {
            @Override
            public void execute(Long previousOutput, final ResultListener<Long> listener) {
                append("Before executing secondTask2");
                SimpleServiceAsync.client.stepTwo(false, previousOutput + "", new ChainedCallback<Long>(listener) {
                    @Override
                    public void onSuccess(Long t) {
                        append("onSuccess secondTask2: " + t);
                        if (t != null) {
                            append("onSuccess secondTask2 logic true");
                        } else {
                            append("onSuccess secondTask2 logic false");
                            listener.abort(); //прекратить дальнейшее выполнение цепочки
                        }
                    }
                });
            }
        }).add(new ChainTask<Long, Long>() {
            @Override
            public void execute(Long previousOutput, ResultListener<Long> listener) {
                append("Before executing secondTask3");
                SimpleServiceAsync.client.stepTwo(false, previousOutput + "", new ChainedCallback<Long>(listener) {
                    @Override
                    public void onSuccess(Long t) {
                        append("onSuccess secondTask3: " + t);
                        if (t != null) {
                            append("onSuccess secondTask3 logic true");
                        } else {
                            append("onSuccess secondTask3 logic false");
                        }
                    }
                });
            }
        }).add(new ChainTask<Long, List<String>>() {
            @Override
            public void execute(Long previousOutput, ResultListener<List<String>> listener) {
                append("Before executing stepThree " + previousOutput);
                SimpleServiceAsync.client.stepThree(previousOutput, new ChainedCallback<List<String>>(listener) {
                    @Override
                    public void onSuccess(List<String> t) {
                        append("onSuccess stepThree: " + t.get(0));
                        if (!t.isEmpty()) {
                            append("onSuccess stepThree logic true");
                        } else {
                            append("onSuccess stepThree logic false");
                        }
                    }
                });
            }
        }).start();
    }
}
