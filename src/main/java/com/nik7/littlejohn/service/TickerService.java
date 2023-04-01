package com.nik7.littlejohn.service;

import com.nik7.littlejohn.resurce.Stock;
import com.nik7.littlejohn.resurce.Ticker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TickerService {

    public List<Stock> getUserStocks(String userAuth) {
        String candidate;
        if (userAuth.length() > 10) {
            candidate = userAuth.substring(0, 10);
        } else {
            candidate = userAuth;
        }
        char[] charArray = candidate.toCharArray();

        Set<String> stocks = new HashSet<>();

        int maxElement = Ticker.values().length;
        for (char c : charArray) {
            Ticker ticker = Ticker.getById(c % maxElement);
            if (ticker != null) {
                stocks.add(ticker.name());
            }
        }

        return stocks.stream()
                .sorted()
                .map(s -> new Stock(s, calculatePrice(s)))
                .collect(Collectors.toList());
    }

    private double calculatePrice(String stockName) {
        return calculatePrice(stockName, LocalDate.now());
    }

    private double calculatePrice(String stockName, LocalDate localDate) {
        LocalDate now = LocalDate.now();
        char[] charArray = stockName.toCharArray();
        double result = 0.0;
        for (int i = 0; i < charArray.length; i++) {
            result += ((double) charArray[i] / (i + 1));
        }
        long time = Math.abs(now.toEpochDay() - localDate.toEpochDay());

        if (time == 0) {
            return result;
        }

        boolean isOdd = (time % 2) != 0;

        double change = (double) (time % 1000) / 100;

        if (!isOdd) {
            result += change;
        } else {
            result -= change;
        }
        return Math.abs(result);
    }
}
