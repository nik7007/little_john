package com.nik7.littlejohn.resurce;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nik7.littlejohn.databind.DoubleToStringSerializer;

import java.time.LocalDate;
import java.util.StringJoiner;

public class TickerHistory {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonSerialize(using = DoubleToStringSerializer.class)
    private double price;

    public TickerHistory() {
    }

    public TickerHistory(LocalDate date, double price) {
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TickerHistory.class.getSimpleName() + "[", "]")
                .add("date=" + date)
                .add("price=" + price)
                .toString();
    }
}
