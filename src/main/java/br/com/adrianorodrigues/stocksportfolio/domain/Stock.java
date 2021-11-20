package br.com.adrianorodrigues.stocksportfolio.domain;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class Stock {

    private Long id;
    private String ticker;

    @Builder.Default
    private BigDecimal priceAvg = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal price = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal amount = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal investedAmount = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal currentAmount = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    public void update(Transaction transaction) {
        priceAvg = priceAvg
                .multiply(amount)
                .add(transaction.getAmount().multiply(transaction.getPrice()))
                .divide(amount.add(transaction.getAmount()), 6, RoundingMode.HALF_EVEN);
        amount = amount.add(transaction.getAmount());
    }

    public void updateWithPrice(BigDecimal price) {
        setPrice(price);
        investedAmount = amount.multiply(priceAvg);
        currentAmount = amount.multiply(price);
        balance = currentAmount.subtract(investedAmount);
    }
}
