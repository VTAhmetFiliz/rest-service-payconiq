package com.payconiq;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService {

    private Map<Long, Stock> stockList = new LinkedHashMap<>();

    @Override
    public Collection<Stock> getStockList() {
        return stockList != null ? stockList.values() : new LinkedHashMap<Long, Stock>().values();
    }

    @Override
    public Stock getStockById(long id) {
        return stockList.containsKey(id) == true ? stockList.get(id) : null;
    }

    @Override
    public void addStock(long id, Stock stock) {
        stockList.put(id, stock);
    }
}
