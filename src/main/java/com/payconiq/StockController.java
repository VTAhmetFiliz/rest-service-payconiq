package com.payconiq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/api/stocks", method = RequestMethod.GET)
    public ResponseEntity<Collection<Stock>> getStockList() {
        Collection<Stock> stockList = stockService.getStockList();
        if (stockList.isEmpty()) {
            return new ResponseEntity("Stock list empty!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/stocks/1/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneStock(@PathVariable("id") long id) {
        Stock currentStock = stockService.getStockById(id);
        if (currentStock == null) {
            return new ResponseEntity("Stock with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentStock, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/stocks/1", method = RequestMethod.PUT)
    public ResponseEntity updateOneStock(@RequestBody Stock stock) {
        Stock currentStock = stockService.getStockById(stock.getId());
        if (currentStock == null) {
            return new ResponseEntity("Unable to update. Stock with id " + stock.getId() + " not found.", HttpStatus.NOT_FOUND);
        }

        currentStock.setName(stock.getName());
        currentStock.setCurrentPrice(stock.getCurrentPrice());
        currentStock.setLastUpdate(new Date());
        return new ResponseEntity<>(currentStock, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/stocks", method = RequestMethod.POST)
    public ResponseEntity addStock(@RequestBody Stock stock, UriComponentsBuilder ucBuilder) {
        stockService.addStock(stock.getId(), stock);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/stocks/1").buildAndExpand(stock).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
