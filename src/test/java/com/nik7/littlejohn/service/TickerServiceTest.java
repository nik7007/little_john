package com.nik7.littlejohn.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TickerServiceTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void testPricingGeneration() {
        LocalDate localDate = LocalDate.parse("01/04/2023", FORMATTER);
        double stockTest = TickerService.calculatePrice("STOCK_TEST", localDate);

        for (int i = 0; i < 1000; i++) {
            assertEquals(stockTest, TickerService.calculatePrice("STOCK_TEST", localDate));
        }
    }

}
