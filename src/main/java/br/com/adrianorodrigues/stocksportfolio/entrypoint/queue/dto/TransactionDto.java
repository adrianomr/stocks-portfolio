package br.com.adrianorodrigues.stocksportfolio.entrypoint.queue.dto;

import br.com.adrianorodrigues.stocksportfolio.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {
    private LocalDate date;
    private String ticker;
    private BigDecimal amount;
    private BigDecimal price;
    private TransactionType type;

    public String getNormalizedTicker() {
        return getTicker().endsWith("F") ?
                getTicker().substring(0, getTicker().length() - 1)
                : getTicker();
    }
}

