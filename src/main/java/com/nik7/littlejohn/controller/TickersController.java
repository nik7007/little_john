package com.nik7.littlejohn.controller;

import com.nik7.littlejohn.exception.InvalidTicker;
import com.nik7.littlejohn.resurce.Stock;
import com.nik7.littlejohn.resurce.Ticker;
import com.nik7.littlejohn.resurce.TickerHistory;
import com.nik7.littlejohn.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                                                @RequestAttribute String userAuth) throws InvalidTicker {
        Ticker ticker;
        try {
            ticker = Ticker.valueOf(stock);
        } catch (IllegalArgumentException e) {
            throw new InvalidTicker(e);
        }

        return null;
    }
}
