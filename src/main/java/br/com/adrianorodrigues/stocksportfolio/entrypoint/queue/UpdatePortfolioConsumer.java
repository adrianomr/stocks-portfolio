package br.com.adrianorodrigues.stocksportfolio.entrypoint.queue;

import br.com.adrianorodrigues.stocksportfolio.adapter.domain.TransactionAdapter;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.queue.dto.UserTransactionsDto;
import br.com.adrianorodrigues.stocksportfolio.usecase.UpdatePortfolio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdatePortfolioConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UpdatePortfolio updatePortfolio;

    @JmsListener(destination = "update-portfolio.fifo")
    public void process(String message) {
        log.info(message);
        try {
            UserTransactionsDto userTransactions = objectMapper
                    .readValue(message, UserTransactionsDto.class);
            updatePortfolio.updatePortfolio(userTransactions.getId(), TransactionAdapter.convert(userTransactions.getTransactions()));
        } catch (JsonProcessingException e) {
            log.error("Error processing update-portfolio.fifo queue", e);
        }
    }

}
