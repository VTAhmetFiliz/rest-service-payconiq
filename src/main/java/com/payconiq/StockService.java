package com.payconiq;

import java.util.Map;

public interface StockService {
    Stock getStockById(long id);

    Map<Long, Stock> getStockList();
}
