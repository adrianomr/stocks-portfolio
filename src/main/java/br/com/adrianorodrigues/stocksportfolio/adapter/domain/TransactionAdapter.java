package br.com.adrianorodrigues.stocksportfolio.adapter.domain;

import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.domain.Transaction;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.queue.dto.TransactionDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class TransactionAdapter {

    public static List<Transaction> convert(List<TransactionDto> transactions) {
        return transactions
                .stream()
                .map(TransactionAdapter::convert)
                .collect(Collectors.toList());
    }

    public static Transaction convert(TransactionDto transactionDto) {
        return Transaction
                .builder()
                .amount(transactionDto.getAmount())
                .price(transactionDto.getPrice())
                .type(transactionDto.getType())
                .stock(Stock.builder().ticker(transactionDto.getTicker()).build())
                .build();
    }

}
