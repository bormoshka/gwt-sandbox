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
import ru.ulmc.gwt.sandbox.client.api.ServiceAsync;
import ru.ulmc.gwt.sandbox.client.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.client.model.SimpleBean;

/*
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
*/

/**
 * Entry Point
 */
public class SimpleEntryPoint implements EntryPoint {

    public SimpleEntryPoint() {
    }

    public void onModuleLoad() {
/*
        final TextField textField = new TextField();
       // textField.setWidth(200);
        TextButton button = new TextButton("Send request!");
        //button.setWidth(200);

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

        VBoxLayoutContainer vblc = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCHMAX);
        vblc.add(textField);
        vblc.add(button);

        CenterLayoutContainer clc = new CenterLayoutContainer();
        clc.add(vblc);

        Window window = new Window();
        window.setHeading("Sending Request");
        window.setWidth(300);
        window.setWidget(clc);
*/
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


        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(label);
        verticalPanel.add(btn);
        verticalPanel.add(btnPing);
        verticalPanel.setSpacing(5);

        RootPanel.get().add(verticalPanel);
    }
}