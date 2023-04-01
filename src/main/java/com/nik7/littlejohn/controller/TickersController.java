package com.nik7.littlejohn.controller;

import com.nik7.littlejohn.resurce.Stock;
import com.nik7.littlejohn.resurce.TickerHistory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickers")
public class TickersController {

    @GetMapping
    public List<Stock> getUserStocks(@RequestAttribute String userAuth) {

        return null;
    }

    @GetMapping("/{stock}/history")
    public List<TickerHistory> getTickerHistory(@PathVariable String stock, @RequestAttribute String userAuth) {

        return null;
    }
}
