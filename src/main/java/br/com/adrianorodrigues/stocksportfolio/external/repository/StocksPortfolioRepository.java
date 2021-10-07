package br.com.adrianorodrigues.stocksportfolio.external.repository;

import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.PortfolioDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksPortfolioRepository extends JpaRepository<StocksPortfolioDto, Long> {

    StocksPortfolioDto findByUserId(Long userId);
}
