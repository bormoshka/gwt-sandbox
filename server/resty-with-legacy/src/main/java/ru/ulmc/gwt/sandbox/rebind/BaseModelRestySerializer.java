package ru.ulmc.gwt.sandbox.rebind;

import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

import com.extjs.gxt.ui.client.data.RpcMap;

import org.fusesource.restygwt.rebind.JsonEncoderDecoderClassCreator;
import org.fusesource.restygwt.rebind.RestyJsonSerializerGenerator;

/**
 *
 */
public class BaseModelRestySerializer implements RestyJsonSerializerGenerator {

    @Override
    public Class<? extends JsonEncoderDecoderClassCreator> getGeneratorClass() {
        return RpcMapEncoderDecoderClassCreator.class;
    }

    @Override
    public JType getType(TypeOracle typeOracle) {
        return typeOracle.findType(RpcMap.class.getName());
    }
}
