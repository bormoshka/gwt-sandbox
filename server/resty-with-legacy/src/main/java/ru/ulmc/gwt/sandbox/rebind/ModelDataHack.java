package ru.ulmc.gwt.sandbox.rebind;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import org.fusesource.restygwt.rebind.JsonEncoderDecoderClassCreator;
import ru.ulmc.gwt.sandbox.shared.model.BaseModelWrapper;

/**
 * не работает
 */
@Deprecated
public class ModelDataHack extends JsonEncoderDecoderClassCreator {
    protected TreeLogger logger;
    protected GeneratorContext context;
    protected JClassType source;
    protected JClassType superType;
    protected boolean swapSource = false;
    protected JsonEncoderDecoderClassCreator puppet;

    public ModelDataHack(TreeLogger logger, GeneratorContext context, JClassType source) {
        super(logger, context, source);
        this.logger = logger;
        this.context = context;
        this.source = source;

        TypeOracle oracle = context.getTypeOracle();
        superType = oracle.findType(ModelData.class.getName());
        JClassType swapClass = oracle.findType(BaseModelWrapper.class.getName());
        swapSource = source.isInterface() != null && source.isAssignableTo(superType);
        puppet = new JsonEncoderDecoderClassCreator(logger, context, swapClass);

    }

    @Override
    public void generate() throws UnableToCompleteException {
        if (swapSource) {
            logger.log(TreeLogger.INFO, "ModelDataHack swapped generator for: " + source.getName());
            puppet.generate();
        } else {
            logger.log(TreeLogger.INFO, "ModelDataHack default generator for: " + source.getName());
            super.generate();

        }
    }

}
