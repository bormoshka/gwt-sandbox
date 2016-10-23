package ru.ulmc.gwt.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ulmc.gwt.sandbox.shared.api.ServiceAsync;
import ru.ulmc.gwt.sandbox.client.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.client.model.SimpleBean;
import ru.ulmc.gwt.sandbox.shared.api.SimpleServiceAsync;

/**
 * Entry Point
 */
public class SimpleEntryPointGWT implements EntryPoint {

    public SimpleEntryPointGWT() {
    }

    public void onModuleLoad() {
        final Label label = new Label("label");
        Button btn = new Button("Send");
        btn.setWidth("100px");
        btn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final SimpleBean bean = new SimpleBean("Groove", 1337L);
                ServiceAsync.client.getBean(bean, new MethodCallback<NotThatSimpleBean>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        label.setText("fail");
                    }

                    @Override
                    public void onSuccess(Method method, NotThatSimpleBean response) {
                        label.setText("received: " + response.get(bean.getFieldString()));
                    }
                });
            }
        });

        Button btnPing = new Button("Send Ping");
        btnPing.setWidth("100px");
        btnPing.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ServiceAsync.client.ping(new MethodCallback<String>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        label.setText("fail ping");
                    }

                    @Override
                    public void onSuccess(Method method, String response) {
                        label.setText("received: " + response);
                    }
                });
            }
        });

        Button simpleServiceBtn = new Button("Simple Service");
        simpleServiceBtn.setWidth("100px");
        simpleServiceBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SimpleServiceAsync.client.hello(new MethodCallback<String>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        exception.printStackTrace();
                        log(exception.getLocalizedMessage());
                        log(exception.getMessage());
                        label.setText("fail ping: " + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, String response) {
                        label.setText("received: " + response);
                    }
                });
            }
        });


        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(label);
        verticalPanel.add(btn);
        verticalPanel.add(btnPing);
        verticalPanel.add(simpleServiceBtn);
        verticalPanel.setSpacing(5);

        RootPanel.get().add(verticalPanel);
    }

    public static native void log(String string) /*-{
        if (window.console != undefined) {
            window.console.log(string);
        }
    }-*/;
}