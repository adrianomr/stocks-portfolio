package br.com.adrianorodrigues.stocksportfolio.usecase;

import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UpdateStockGradeUseCaseImpl implements UpdateStockGradeUseCase {

    @Autowired
    StocksRepository repository;

    @Override
    @Transactional
    public Stock execute(Stock stock) {
        StockDto stockDto = repository
                .findById(stock.getId())
                .orElseThrow(() -> new RuntimeException("stock not found"));
        stockDto.setGrade(stock.getGrade());

        repository.save(stockDto);

        return stock;
    }

}
