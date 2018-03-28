package com.payconiq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerTests {

    public static String GET_ADD_SERVICE_URI = "/api/stocks";
    public static String GET_ONE_SERVICE_URI = "/api/stocks/1/{id}";
    public static String UPDATE_SERVICE_URI = "/api/stocks/1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockService stockService;

    @Before
    public void setUp() {
        stockService.getStockList().clear();
    }

    @Test
    public void getEmptyStockListTest() throws Exception {
        this.mockMvc.perform(get(GET_ADD_SERVICE_URI))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putTwoStocksTest() throws Exception {
        Stock stockOne = new Stock(1, "stock1", 50);
        Stock stockTwo = new Stock(2, "stock2", 60);

        this.mockMvc.perform(post(GET_ADD_SERVICE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(stockOne)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(post(GET_ADD_SERVICE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(stockTwo)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get(GET_ADD_SERVICE_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("stock1")))
                .andExpect(jsonPath("$[0].currentPrice", is(50)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("stock2")))
                .andExpect(jsonPath("$[1].currentPrice", is(60)));
    }

    @Test
    public void updateStockSuccessfulTest() throws Exception {
        Stock stock = new Stock(1, "stock", 50);
        Stock updatedStock = new Stock(1, "updatedStock", 60);
        this.mockMvc.perform(post(GET_ADD_SERVICE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(stock)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(put(UPDATE_SERVICE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedStock)))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(GET_ADD_SERVICE_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("updatedStock")))
                .andExpect(jsonPath("$[0].currentPrice", is(60)));
    }

    @Test
    public void updateStockUnsuccessfulTest() throws Exception {
        Stock stock = new Stock(1, "stock", 50);
        Stock updatedStock = new Stock(2, "updatedStock", 60);
        this.mockMvc.perform(post(GET_ADD_SERVICE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(stock)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(put(UPDATE_SERVICE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedStock)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getOneStockSuccessfulTest() throws Exception {
        Stock stock = new Stock(1, "stock", 50);
        this.mockMvc.perform(post(GET_ADD_SERVICE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(stock)))
                .andExpect(status().isCreated());
        this.mockMvc.perform(get(GET_ONE_SERVICE_URI, 1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("stock")))
                .andExpect(jsonPath("$.currentPrice", is(50)));
    }

    @Test
    public void getOneStockUnsuccessfulTest() throws Exception {
        this.mockMvc.perform(get(GET_ONE_SERVICE_URI, 1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
