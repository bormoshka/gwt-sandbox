package ru.ulmc.gwt.sandbox.rebind;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONObject;

import com.extjs.gxt.ui.client.data.RpcMap;

import org.fusesource.restygwt.rebind.JsonEncoderDecoderClassCreator;

import ru.ulmc.gwt.sandbox.shared.model.BaseModelWrapper;

/**
 * Сериализатор RpcMap. Черновой вариант - решение "в лоб"
 */
public class RpcMapEncoderDecoderClassCreator extends JsonEncoderDecoderClassCreator {
    protected TreeLogger logger;
    protected GeneratorContext context;
    protected JClassType source;
    protected JClassType superType;
    protected JClassType rpcMap;
    protected static final String JSON_OBJECT_CLASS = JSONObject.class.getName();
    protected static final String JSON_ARRAY_CLASS = JSONArray.class.getName();
    protected static final String JSON_NULL_CLASS = JSONNull.class.getName();

    public RpcMapEncoderDecoderClassCreator(TreeLogger logger, GeneratorContext context, JClassType source) {
        super(logger, context, source);
        this.logger = logger;
        this.context = context;
        this.source = source;

        TypeOracle oracle = context.getTypeOracle();
        rpcMap = oracle.findType(RpcMap.class.getName());
        superType = oracle.findType(BaseModelWrapper.class.getName());
    }

    @Override
    public void generate() throws UnableToCompleteException {
       /* if (source.isAssignableTo(superType)) {
            logger.log(TreeLogger.INFO, "RpcMapEncoderDecoderClassCreator Found subtype: " + source.getName());
            super.generate();
        } else */
        if (source.isAssignableFrom(rpcMap)) {
            logger.log(TreeLogger.INFO, "RpcMapEncoderDecoderClassCreator Found subtype of RpcMap: " + source.getName());
            generateRPCMapEncoderDecoder();
        } else {
            logger.log(TreeLogger.INFO, "RpcMapEncoderDecoderClassCreator default generator for: " + source.getName());
            super.generate();
        }
    }

    public void generateRPCMapEncoderDecoder() {
        generateSingleton(shortName);
        p("public com.google.gwt.json.client.JSONValue encode(com.extjs.gxt.ui.client.data.RpcMap value) {");
        p(" if( value==null ) {\n" +
                "      return getNullType();\n" +
                "    }\n" +
                "    com.google.gwt.json.client.JSONObject rc = new com.google.gwt.json.client.JSONObject();\n" +
                "    com.extjs.gxt.ui.client.data.RpcMap parseValue = (com.extjs.gxt.ui.client.data.RpcMap)value;\n" +
                "    for(String key: parseValue.keySet()) {\n" +
                "        rc.put(key, org.fusesource.restygwt.client.ObjectEncoderDecoder.INSTANCE.encode(parseValue.get(key)));\n" +
                "    }\n" +
                "    return rc;\n" +
                "}\n\n");
        p("public com.extjs.gxt.ui.client.data.RpcMap decode(com.google.gwt.json.client.JSONValue value) {\n" +
                "    if( value == null || value.isNull()!=null ) {\n" +
                "      return null;\n" +
                "    }\n" +
                "    com.google.gwt.json.client.JSONObject object = toObject(value);\n" +
                "    com.extjs.gxt.ui.client.data.RpcMap rc = new com.extjs.gxt.ui.client.data.RpcMap();\n" +
                "    for(String key: object.keySet()) {\n" +
                "        rc.put(key, org.fusesource.restygwt.client.ObjectEncoderDecoder.INSTANCE.decode(object.get(key)));\n" +
                "    }\n" +
                "    return rc;\n" +
                "  }");
    }
}
