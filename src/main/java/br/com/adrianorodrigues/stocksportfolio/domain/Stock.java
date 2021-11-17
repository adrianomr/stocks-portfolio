package br.com.adrianorodrigues.stocksportfolio.domain;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class Stock {

    private Long id;
    private String ticker;

    @Builder.Default
    private BigDecimal price = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal amount = BigDecimal.ZERO;

    public void update(Transaction transaction) {
        amount = amount.add(transaction.getAmount());
    }
}
