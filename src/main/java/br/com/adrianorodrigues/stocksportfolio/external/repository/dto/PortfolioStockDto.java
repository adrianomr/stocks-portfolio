package br.com.adrianorodrigues.stocksportfolio.external.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PortfolioStockDto {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private BigDecimal amount;

    @Column
    private BigDecimal priceAvg;

    @ManyToOne
    StockDto stock;

}
