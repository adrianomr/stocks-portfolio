package br.com.adrianorodrigues.stocksportfolio.external.repository;

import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StocksPortfolioRepository extends JpaRepository<StocksPortfolioDto, Long> {

    Optional<StocksPortfolioDto> findByUserId(Long userId);
}
