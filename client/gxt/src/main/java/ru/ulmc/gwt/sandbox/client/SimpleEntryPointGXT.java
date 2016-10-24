package ru.ulmc.gwt.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import ru.ulmc.gwt.sandbox.shared.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBean;

/**
 * Entry Point GXT realisation
 */
public class SimpleEntryPointGXT implements EntryPoint {

    public SimpleEntryPointGXT() {
    }

    public void onModuleLoad() {

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

        RootPanel.get().add(window);
    }
}