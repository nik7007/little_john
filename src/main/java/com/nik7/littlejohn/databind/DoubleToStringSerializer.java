package com.nik7.littlejohn.databind;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DoubleToStringSerializer extends ToStringSerializerBase {
    public DoubleToStringSerializer() {
        super(null);
    }

    @Override
    public String valueToString(Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Double) {
            return new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
                    .format(value);
        }
        return value.toString();
    }
}
