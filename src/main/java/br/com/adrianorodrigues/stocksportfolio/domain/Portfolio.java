package br.com.adrianorodrigues.stocksportfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Portfolio {

    private Long id;
    @Builder.Default
    private List<Stock> stocks = new ArrayList<>();
    private Long userId;

    public void update(List<Transaction> transactions) {
        transactions
                .forEach(this::update);
    }

    private void update(Transaction transaction) {
        Stock stock = findStock(transaction)
                .orElse(addStock(transaction));

        stock.update(transaction);
    }

    private Stock addStock(Transaction transaction) {
        stocks.add(transaction.getStock());
        return transaction.getStock();
    }

    private Optional<Stock> findStock(Transaction transaction) {
        return stocks
                .stream()
                .filter(stock -> transaction.getStock().equals(stock))
                .findAny();
    }
}
