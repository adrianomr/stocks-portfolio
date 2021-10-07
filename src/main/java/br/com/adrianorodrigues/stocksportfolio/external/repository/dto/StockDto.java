package br.com.adrianorodrigues.stocksportfolio.external.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StockDto {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    String ticker;

}
