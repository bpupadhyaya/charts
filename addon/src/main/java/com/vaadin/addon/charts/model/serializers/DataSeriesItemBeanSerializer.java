package com.vaadin.addon.charts.model.serializers;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.vaadin.addon.charts.model.DataSeriesItem;

/**
 * Custom bean serializer for {@link DataSeriesItem}
 *
 */
public class DataSeriesItemBeanSerializer extends
        BeanSerializationDelegate<DataSeriesItem> {

    @Override
    public Class<DataSeriesItem> getBeanClass() {
        return DataSeriesItem.class;
    }

    @Override
    public void serialize(DataSeriesItem bean,
            BeanSerializerDelegator<DataSeriesItem> serializer,
            JsonGenerator jgen, SerializerProvider provider) throws IOException {

        if (bean.isCustomized()) {
            jgen.writeStartObject();
            // write fields as per normal serialization rules
            serializer.serializeFields(bean, jgen, provider);
            jgen.writeEndObject();

        } else {
            Number x = bean.getX();
            Number y = bean.getY();
            if (x != null) {
                ArrayNode jsonArray = JsonNodeFactory.instance.arrayNode();
                jsonArray.addPOJO(x);
                if (y != null) {
                    jsonArray.addPOJO(y);
                } else if (bean.getLow() != null) {
                    jsonArray.addPOJO(bean.getLow());
                    jsonArray.addPOJO(bean.getHigh());
                } else {
                    jsonArray.addNull();
                    jsonArray.addNull();
                }
                jgen.writeTree(jsonArray);
            } else {
                // If no x set, make it like list series, just number or
                // min-max pairs
                if (y != null) {
                    jgen.writeObject(y);
                } else {
                    ArrayNode jsonArray = JsonNodeFactory.instance.arrayNode();
                    jsonArray.addPOJO(bean.getLow());
                    jsonArray.addPOJO(bean.getHigh());
                    jgen.writeTree(jsonArray);
                }
            }
        }
    }
}
