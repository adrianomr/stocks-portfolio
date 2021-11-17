package br.com.adrianorodrigues.stocksportfolio.external.repository.dto;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "stocks_portfolio")
@EqualsAndHashCode
public class StocksPortfolioDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Builder.Default
    private List<StockDto> stocks = new ArrayList<>();

    @Column
    private Long userId;

}
