package com.payconiq;

import java.util.Collection;

public interface StockService {
    Stock getStockById(long id);

    Collection<Stock> getStockList();

    void addStock(long id, Stock stock);
}
