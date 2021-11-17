package br.com.adrianorodrigues.stocksportfolio.external.repository.dto;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    StocksPortfolioDto portfolio;

}
