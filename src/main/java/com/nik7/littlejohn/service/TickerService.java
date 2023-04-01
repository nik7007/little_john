package com.nik7.littlejohn.service;

import com.nik7.littlejohn.resurce.Stock;
import com.nik7.littlejohn.resurce.Ticker;
import com.nik7.littlejohn.resurce.TickerHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private static double calculatePrice(String stockName) {
        return calculatePrice(stockName, LocalDate.now());
    }

    private static double calculatePrice(String stockName, LocalDate localDate) {
        char[] charArray = stockName.toCharArray();
        double result = 0.0;
        for (int i = 0; i < charArray.length; i++) {
            result += ((double) charArray[i] / (i + 1));
        }
        long time = localDate.toEpochDay();

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

    public List<TickerHistory> getLastTickerValues(Ticker ticker, int days, LocalDate startDay) {
        List<LocalDate> dates = new ArrayList<>(90);

        for (int day = 0; day < days; day++) {
            dates.add(startDay.minusDays(day));
        }

        return dates.stream()
                .map(date -> new TickerHistory(date, calculatePrice(ticker.name(), date)))
                .collect(Collectors.toList());
    }
}
