package br.com.adrianorodrigues.stocksportfolio.external.repository.dto;

import br.com.adrianorodrigues.stocksportfolio.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class StockDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    String ticker;
    @Column
    BigDecimal priceAvg;
    @Column
    BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY)
    StocksPortfolioDto portfolio;

}
