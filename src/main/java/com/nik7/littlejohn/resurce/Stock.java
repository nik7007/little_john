package com.nik7.littlejohn.resurce;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nik7.littlejohn.databind.DoubleToStringSerializer;

import java.util.StringJoiner;

public class Stock {
    private String symbol;
    @JsonSerialize(using = DoubleToStringSerializer.class)
    private double price;

    public Stock() {
    }

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Stock.class.getSimpleName() + "[", "]")
                .add("symbol='" + symbol + "'")
                .add("price='" + price + "'")
                .toString();
    }
}
