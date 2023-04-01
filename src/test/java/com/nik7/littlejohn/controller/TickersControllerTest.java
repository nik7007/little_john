package com.nik7.littlejohn.controller;

import com.nik7.littlejohn.BaseIntegratedTestClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class TickersControllerTest extends BaseIntegratedTestClass {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUserStocksOk() throws Exception {
        mockMvc.perform(get("/tickers")
                        .header("Authorization", "Basic dGVzdDo="))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testGetUserStocksKo() throws Exception {
        mockMvc.perform(get("/tickers")
                        .header("Authorization", "Basic xxx"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void testGetUserStocksKo2() throws Exception {
        mockMvc.perform(get("/tickers")
                        .header("Authorization", "dGVzdDo="))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
