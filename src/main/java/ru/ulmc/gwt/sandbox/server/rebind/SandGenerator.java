package ru.ulmc.gwt.sandbox.server.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;

/**
 * Created by 45 on 20.10.2016.
 */
public class SandGenerator extends Generator {
    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
        TypeOracle typeOracle = context.getTypeOracle();
        JClassType remoteService = typeOracle.findType(typeName);

        ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(remoteService.getPackage().getName(), remoteService.getName()+"Impl");

        composer.addImport(remoteService.getPackage().getName() + ".*");

        PrintWriter pw = context.tryCreate(logger, remoteService.getPackage().getName(), remoteService.getName());

        if (pw != null) {
            SourceWriter sw = composer.createSourceWriter(context, pw);

            sw.println("private String hello= \"Hello!\";");

            return typeName+"Impl";

        }
        return null;
    }
}
