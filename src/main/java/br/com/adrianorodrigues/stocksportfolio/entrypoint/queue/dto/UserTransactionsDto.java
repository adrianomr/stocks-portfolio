package br.com.adrianorodrigues.stocksportfolio.entrypoint.queue.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserTransactionsDto {

    private Long id;
    private List<TransactionDto> transactions;

}
