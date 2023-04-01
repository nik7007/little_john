package com.nik7.littlejohn.databind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleToStringSerializerTest {

    @Test
    public void testFormat() {
        DoubleToStringSerializer serializer = new DoubleToStringSerializer(null);
        assertEquals("", serializer.valueToString(null));
        assertEquals("0.00", serializer.valueToString(0.0));
        assertEquals("1.20", serializer.valueToString(1.2005));
        assertEquals("1.21", serializer.valueToString(1.205));
        assertEquals("154.30", serializer.valueToString(154.3));
        assertEquals("3478.05", serializer.valueToString(3478.0455));
    }

}
