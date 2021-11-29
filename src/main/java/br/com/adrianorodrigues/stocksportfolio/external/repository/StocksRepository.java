package br.com.adrianorodrigues.stocksportfolio.external.repository;

import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface StocksRepository extends JpaRepository<StockDto, Long> {

    @Modifying
    @Query("update StockDto stock set stock.grade = :grade where id = :id")
    void updateStockGrade(@Param("id")Long id, @Param("grade") BigDecimal grade);
}
