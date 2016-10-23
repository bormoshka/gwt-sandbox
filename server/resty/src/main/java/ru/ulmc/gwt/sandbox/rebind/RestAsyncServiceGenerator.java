package ru.ulmc.gwt.sandbox.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.RebindMode;
import com.google.gwt.core.ext.RebindResult;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Генератор реализации клиента для отправки ассинхронных запросов
 */
public class RestAsyncServiceGenerator extends Generator {
    private static Map<String, String> generatedClassNames = new HashMap<>();

    public String generate(TreeLogger logger, GeneratorContext ctx, String requestedClass)
            throws UnableToCompleteException {

        TypeOracle typeOracle = ctx.getTypeOracle();
        assert (typeOracle != null);

        JClassType remoteService = typeOracle.findType(requestedClass);
        if (remoteService == null) {
            logger.log(TreeLogger.ERROR, "Unable to find metadata for type '" + requestedClass + "'", null);
            throw new UnableToCompleteException();
        }

        if (remoteService.isInterface() == null) {
            logger.log(TreeLogger.ERROR, remoteService.getQualifiedSourceName() + " is not an interface", null);
            throw new UnableToCompleteException();
        }

        TreeLogger proxyLogger = logger.branch(TreeLogger.DEBUG,
                "Generating client proxy for remote service interface '"
                        + remoteService.getQualifiedSourceName() + "'", null);

        final String genPackageName = remoteService.getPackage().getName();
        final String genClassName = remoteService.getName().replace(".", "") + "Impl";

        ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(
                genPackageName, genClassName);
        composerFactory.addImport("ru.ulmc.gwt.sandbox.client.model.*");
        composerFactory.addImport("org.fusesource.restygwt.client.*");
        composerFactory.addImplementedInterface(remoteService.getPackage().getName()
                + "." + remoteService.getName());

        PrintWriter pw = ctx.tryCreate(logger, genPackageName, genClassName);
        if (pw != null) {
            SourceWriter sw = composerFactory.createSourceWriter(ctx, pw);
            createInstance(sw, remoteService);
            rebindMethods(sw, remoteService);
            sw.commit(proxyLogger);
            RebindResult rebindResult = new RebindResult(RebindMode.USE_ALL_NEW_WITH_NO_CACHING,
                    genPackageName + "." + genClassName);

            proxyLogger.log(TreeLogger.INFO, rebindResult.getResultTypeName());
            generatedClassNames.put(remoteService.getName(), rebindResult.getResultTypeName());
        }
        return generatedClassNames.get(remoteService.getName());
    }

    private void createInstance(SourceWriter sw, JClassType remoteService) {
        String serviceInterface = remoteService.getName().replace("Async", "");
       // sw.println("static " + serviceInterface + " instance = " + serviceInterface + ".instance;");
        sw.println("static {");
        sw.indent();
        sw.println("Resource resource = new Resource(" + serviceInterface + ".CONTROLLER_URL);");
        sw.println("((RestServiceProxy) instance).setResource(resource);");
        sw.println("}");
        sw.outdent();
    }

    private void rebindMethods(SourceWriter sw, JClassType remoteService) {
        JMethod[] methods = remoteService.getMethods();
        for (JMethod method : methods) {
            String parameters = "";
            String parametersWithoutCallback = "";
            //String callback = ""; //todo: определять название параметра колбэка
            int paramCount = method.getParameters().length;
            for (JParameter param : method.getParameters()) {
                parameters += param.getType().getParameterizedQualifiedSourceName() + " " + param.getName();

                if (paramCount > 1) {
                    parametersWithoutCallback += param.getName() + (paramCount > 2 ? ", " : "");
                }
                if (--paramCount > 0) {
                    parameters += ",";
                }

            }
            sw.println("public void " + method.getName() + "(" + parameters + ") {");
            sw.indent();
            sw.println("try {");
            sw.indent();
            sw.println("REST.withCallback(callback).call(instance)." + method.getName() + "(" + parametersWithoutCallback + ");");
            sw.outdent();
            sw.println("} catch (Exception e) {");
            sw.indent();
            sw.println("e.printStackTrace();");
            sw.outdent();
            sw.println("}");
            sw.outdent();
            sw.println("}");
        }
    }
}
