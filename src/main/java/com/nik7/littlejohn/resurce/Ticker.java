package com.nik7.littlejohn.resurce;

import java.util.Arrays;

public enum Ticker {
    AAPL(0),
    MSFT(1),
    GOOG(2),
    AMZN(3),
    FB(4),
    TSLA(5),
    NVDA(6),
    JPM(7),
    BABA(8),
    JNJ(9),
    WMT(10),
    PG(11),
    PYPL(12),
    DIS(13),
    ADBE(14),
    PFE(15),
    V(16),
    MA(17),
    CRM(18),
    NFLX(19);

    private final int id;

    Ticker(int id) {
        this.id = id;
    }

    public static Ticker getById(int id) {
        return Arrays.stream(Ticker.values())
                .filter(t -> t.id == id)
                .findFirst()
                .orElse(null);
    }

    public int getId() {
        return id;
    }
}
