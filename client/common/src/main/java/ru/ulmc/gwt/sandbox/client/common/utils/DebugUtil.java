package ru.ulmc.gwt.sandbox.client.common.utils;

/**
 *
 */
public class DebugUtil {
    public static native void log(Object obj) /*-{
        if(window.console != undefined) {
            window.console.log(obj);
        }
    }-*/;
}
