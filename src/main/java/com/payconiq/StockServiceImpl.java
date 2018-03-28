package com.payconiq;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService {

    private Map<Long, Stock> stockList = new HashMap<>();

    @Override
    public Map<Long, Stock> getStockList() {
        return stockList != null ? stockList : new HashMap();
    }

    @Override
    public Stock getStockById(long id) {
        return stockList.containsKey(id) == true ? stockList.get(id) : null;
    }
}
