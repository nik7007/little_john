package com.nik7.littlejohn.controller;

import com.nik7.littlejohn.exception.InvalidPageException;
import com.nik7.littlejohn.exception.InvalidTickerException;
import com.nik7.littlejohn.resurce.Stock;
import com.nik7.littlejohn.resurce.Ticker;
import com.nik7.littlejohn.resurce.TickerHistory;
import com.nik7.littlejohn.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tickers")
public class TickersController {
    private final TickerService tickerService;

    @Autowired
    public TickersController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @GetMapping
    public List<Stock> getUserStocks(@RequestAttribute String userAuth) {
        return tickerService.getUserStocks(userAuth);
    }

    @GetMapping("/{stock}/history")
    public List<TickerHistory> getTickerHistory(@PathVariable String stock,
                                                @RequestParam(required = false) Integer page,
                                                @RequestAttribute String userAuth) throws InvalidTickerException, InvalidPageException {
        Ticker ticker;
        try {
            ticker = Ticker.valueOf(stock);
        } catch (IllegalArgumentException e) {
            throw new InvalidTickerException(e);
        }

        int pagination = 90;
        LocalDate startDate;
        if (page == null) {
            startDate = LocalDate.now();
        } else {
            startDate = LocalDate.now().minusDays(90L * page);
        }

        if (startDate.isBefore(LocalDate.now().minusYears(10))) {
            throw new InvalidPageException("Cannot retrieve data less than 10 years!");
        }

        return tickerService.getLastTickerValues(ticker, pagination, startDate);
    }
}
