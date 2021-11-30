package br.com.adrianorodrigues.stocksportfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Portfolio {

    private Long id;
    @Builder.Default
    private List<Stock> stocks = new ArrayList<>();
    @Builder.Default
    private BigDecimal investedAmount = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal currentAmount = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal totalGrade = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;
    private Long userId;

    public void update(List<Transaction> transactions) {
        transactions
                .forEach(this::update);
    }

    private void update(Transaction transaction) {
        Stock stock = findStock(transaction)
                .orElseGet(() -> addStock(transaction));

        stock.updateWithTransaction(transaction);
    }

    private Stock addStock(Transaction transaction) {
        stocks.add(transaction.getStock());
        return transaction.getStock();
    }

    private Optional<Stock> findStock(Transaction transaction) {
        return stocks
                .stream()
                .filter(stock -> transaction.getStock().getTicker().equalsIgnoreCase(stock.getTicker()))
                .findAny();
    }

    public void summaryze(Stock stock) {
        investedAmount = investedAmount.add(stock.getInvestedAmount());
        currentAmount = currentAmount.add(stock.getCurrentAmount());
        balance = balance.add(stock.getBalance());
        totalGrade = totalGrade.add(Objects.isNull(stock.getGrade()) ? BigDecimal.ZERO : stock.getGrade());
    }

    public void rebalancing(Stock stock) {
        stock.updateTargetAmount(this.currentAmount, this.totalGrade);
    }
}
