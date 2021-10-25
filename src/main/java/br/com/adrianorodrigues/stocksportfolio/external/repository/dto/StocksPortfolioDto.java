package br.com.adrianorodrigues.stocksportfolio.external.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "stocks_portfolio")
public class StocksPortfolioDto {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "stocks_portfolios",
            joinColumns = { @JoinColumn(name = "stock_id") },
            inverseJoinColumns = { @JoinColumn(name = "portfolio_id") }
    )
    private List<StockDto> stocks;
    private Long userId;

}
