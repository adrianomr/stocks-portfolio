package br.com.adrianorodrigues.stocksportfolio.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void update() {
        Stock stock = Stock.builder().priceAvg(BigDecimal.TEN).amount(BigDecimal.ONE).build();
        stock.updateWithTransaction(Transaction.builder().amount(BigDecimal.valueOf(5)).price(BigDecimal.valueOf(5)).build());

        assertEquals(BigDecimal.valueOf(5.833333), stock.getPriceAvg());
    }
}