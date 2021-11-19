package br.com.adrianorodrigues.stocksportfolio.entrypoint.queue.dto;

import br.com.adrianorodrigues.stocksportfolio.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDto {
    private LocalDate date;
    private String ticker;
    private BigDecimal amount;
    private BigDecimal price;
    private TransactionType type;
}

