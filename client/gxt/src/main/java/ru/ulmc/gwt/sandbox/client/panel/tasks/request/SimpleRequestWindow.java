package ru.ulmc.gwt.sandbox.client.panel.tasks.request;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextArea;
import ru.ulmc.gwt.sandbox.client.common.tasks.BaseCallback;
import ru.ulmc.gwt.sandbox.client.common.tasks.Listener;
import ru.ulmc.gwt.sandbox.client.common.tasks.Task;
import ru.ulmc.gwt.sandbox.shared.api.SimpleServiceAsync;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBean;

public class SimpleRequestWindow extends Window {

    public SimpleRequestWindow() {
        final TextArea textArea = new TextArea();
        textArea.setWidth(300);
        textArea.setHeight(100);
        TextButton button = new TextButton("Send request!");
        //button.setWidth(200);

        button.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                final SimpleBean bean = new SimpleBean("Groove", 1337L);
                new Task() {
                    @Override
                    public void execute(Listener listener) {
                        SimpleServiceAsync.client.waitForMe(new BaseCallback<String>(listener) {
                            @Override
                            public void onFailure(Throwable throwable) {
                                new AlertMessageBox("Error", throwable.getMessage()).show();
                            }

                            @Override
                            public void onSuccess(String s) {
                                new MessageBox("onSuccess", "Hooray! It's alive!").show();
                            }
                        });
                    }
                }.start();
            }
        });

        VBoxLayoutContainer vblc = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCHMAX);
        vblc.add(textArea);
        vblc.add(button);

        CenterLayoutContainer clc = new CenterLayoutContainer();
        clc.add(vblc);

        setHeading("Sending Request2");
        setWidth(300);
        setHeight(150);
        add(clc);
        show();
    }
}
