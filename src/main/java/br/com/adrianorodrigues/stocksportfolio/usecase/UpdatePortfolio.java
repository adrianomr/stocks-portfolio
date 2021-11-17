package br.com.adrianorodrigues.stocksportfolio.usecase;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.domain.Transaction;

import java.util.List;

public interface UpdatePortfolio {

    public Portfolio updatePortfolio(Long userId, List<Transaction> transactions);

}
