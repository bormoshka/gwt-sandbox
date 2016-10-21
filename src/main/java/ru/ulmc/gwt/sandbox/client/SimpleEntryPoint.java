package ru.ulmc.gwt.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import ru.ulmc.gwt.sandbox.client.api.ServiceAsync;
import ru.ulmc.gwt.sandbox.client.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.client.model.SimpleBean;

/**
 * Entry Point
 */
public class SimpleEntryPoint implements EntryPoint {

    public SimpleEntryPoint() {
    }

    public void onModuleLoad() {

        final TextField textField = new TextField();
        textField.setWidth(200);
        TextButton button = new TextButton("Send request!");
        button.setWidth(200);
        button.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                final SimpleBean bean = new SimpleBean("Groove", 1337L);
                ServiceAsync.client.getBean(bean, new MethodCallback<NotThatSimpleBean>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        textField.setValue("fail");
                    }

                    @Override
                    public void onSuccess(Method method, NotThatSimpleBean response) {
                        textField.setValue("received: " + response.get(bean.getFieldString()));
                    }
                });
            }
        });

        FlowLayoutContainer flc = new FlowLayoutContainer();
        flc.add(textField);
        flc.add(button);

        ContentPanel cp = new ContentPanel();
        cp.setTitle("Sending Request");
        cp.setWidth(200);
        cp.add(flc);

        CenterLayoutContainer clc = new CenterLayoutContainer();
        clc.add(cp);
        RootPanel.get().add(clc);
    }
}