package br.com.adrianorodrigues.stocksportfolio.external.repository.dto;

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
    @Column
    BigDecimal grade;
    @ManyToOne(fetch = FetchType.EAGER)
    StocksPortfolioDto portfolio;

}
