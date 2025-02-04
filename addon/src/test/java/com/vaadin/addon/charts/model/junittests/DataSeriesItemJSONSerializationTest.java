package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.serializers.DataSeriesItemBeanSerializer;

/**
 * Tests for {@link DataSeriesItemBeanSerializer}
 *
 */
public class DataSeriesItemJSONSerializationTest {

    @Test
    public void toString_cursorIsSet_ItemSerializedWithCursor() {
        DataSeriesItem item = new DataSeriesItem();
        item.setCursor("progress");

        DataSeries series = new DataSeries();

        series.add(item);

        String expected = "{\"data\":[{\"cursor\":\"progress\"}]}";
        assertEquals(expected, toJSON(series));
    }

    @Test
    public void toString_xIsSet_ItemSerializedWithXAndNulls() {
        DataSeriesItem item = new DataSeriesItem();
        item.setX(2);

        DataSeries series = new DataSeries();

        series.add(item);

        String expected = "{\"data\":[[2,null,null]]}";
        assertEquals(expected, toJSON(series));
    }

    @Test
    public void toString_xAndyAreSet_ItemSerializedWithXYAndNull() {
        DataSeriesItem item = new DataSeriesItem();
        item.setX(2);
        item.setY(3);

        DataSeries series = new DataSeries();

        series.add(item);

        String expected = "{\"data\":[[2,3]]}";
        assertEquals(expected, toJSON(series));
    }

    @Test
    public void toString_xAndLowAndHighAreSet_ItemSerializedWithXAndLowAndHigh() {
        DataSeriesItem item = new DataSeriesItem();
        item.setX(2);
        item.setLow(3);
        item.setHigh(4);

        DataSeries series = new DataSeries();

        series.add(item);

        String expected = "{\"data\":[[2,3,4]]}";
        assertEquals(expected, toJSON(series));
    }

    @Test
    public void toString_yIsSet_ItemSerializedWithY() {
        DataSeriesItem item = new DataSeriesItem();
        item.setY(2);

        DataSeries series = new DataSeries();

        series.add(item);

        String expected = "{\"data\":[2]}";
        assertEquals(expected, toJSON(series));
    }

    @Test
    public void toString_lowAndHighSet_ItemSerializedAsArrayWithLowAndHigh() {
        DataSeriesItem item = new DataSeriesItem();
        item.setLow(2);
        item.setHigh(3);

        DataSeries series = new DataSeries();

        series.add(item);

        String expected = "{\"data\":[[2,3]]}";
        assertEquals(expected, toJSON(series));
    }
}
