package br.com.adrianorodrigues.stocksportfolio.usecase;

import br.com.adrianorodrigues.stocksportfolio.adapter.domain.PortfolioAdapter;
import br.com.adrianorodrigues.stocksportfolio.adapter.external.repository.StocksPortfolioDtoAdapter;
import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.domain.Transaction;
import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdatePortfolioImpl implements UpdatePortfolio{

    @Autowired
    StocksPortfolioRepository stocksPortfolioRepository;


    @Override
    public Portfolio updatePortfolio(Long userId, List<Transaction> transactions) {
        Portfolio portfolio = getPortfolio(userId).orElse(createPortfolio(userId));

        portfolio.update(transactions);

        stocksPortfolioRepository.save(StocksPortfolioDtoAdapter.convert(portfolio));

        return portfolio;
    }

    private Portfolio createPortfolio(Long userId) {
        return PortfolioAdapter
                .INSTACE.convert(stocksPortfolioRepository
                .save(StocksPortfolioDto.builder().userId(userId).build()));
    }

    private Optional<Portfolio> getPortfolio(Long userId) {
        return stocksPortfolioRepository
                .findByUserId(userId)
                .map(PortfolioAdapter
                        .INSTACE::convert);
    }
}
