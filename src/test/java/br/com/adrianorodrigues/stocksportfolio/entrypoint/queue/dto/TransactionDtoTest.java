package br.com.adrianorodrigues.stocksportfolio.entrypoint.queue.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDtoTest {

    @Test
    void getNormalizedTicker() {
        TransactionDto transactionDto = TransactionDto
                .builder()
                .ticker("B3SA3")
                .build();

        assertEquals("B3SA3", transactionDto.getNormalizedTicker());
    }

    @Test
    void getNormalizedTickerWhenEndsWithFShouldRemoveF() {
        TransactionDto transactionDto = TransactionDto
                .builder()
                .ticker("B3SA3F")
                .build();

        assertEquals("B3SA3", transactionDto.getNormalizedTicker());
    }
}