package br.com.adrianorodrigues.stocksportfolio.external.repository;

import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockDto, Long> {

}
