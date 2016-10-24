package ru.ulmc.gwt.sandbox.rebind;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcMap;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import org.fusesource.restygwt.rebind.JsonEncoderDecoderClassCreator;
import org.fusesource.restygwt.rebind.RestyJsonSerializerGenerator;

/**
 *
 */
@Deprecated
public class ModelDataHackSerializer implements RestyJsonSerializerGenerator {

    @Override
    public Class<? extends JsonEncoderDecoderClassCreator> getGeneratorClass() {
        return ModelDataHack.class;
    }

    @Override
    public JType getType(TypeOracle typeOracle) {
        return typeOracle.findType(ModelData.class.getName());
    }
}
