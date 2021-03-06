package br.com.adrianorodrigues.stocksportfolio.domain;

import br.com.adrianorodrigues.stocksportfolio.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    private Stock stock;
    private BigDecimal amount;
    private BigDecimal price;
    private TransactionType type;

}
