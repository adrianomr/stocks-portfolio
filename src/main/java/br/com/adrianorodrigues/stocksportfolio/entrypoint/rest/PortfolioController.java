package br.com.adrianorodrigues.stocksportfolio.entrypoint.rest;

import br.com.adrianorodrigues.stocksportfolio.adapter.domain.StockAdapter;
import br.com.adrianorodrigues.stocksportfolio.adapter.entrypoint.rest.PortfolioDtoAdapter;
import br.com.adrianorodrigues.stocksportfolio.adapter.entrypoint.rest.StockDtoAdapter;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.PortfolioDto;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.usecase.GetPortfolioUseCase;
import br.com.adrianorodrigues.stocksportfolio.usecase.UpdateStockGradeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portfolio")
public class PortfolioController {

    @Autowired
    GetPortfolioUseCase getPortfolioUseCase;

    @Autowired
    UpdateStockGradeUseCase updateStockGradeUseCase;

    @GetMapping
    public PortfolioDto getPortfolio() {
        return PortfolioDtoAdapter
                .INSTACE
                .convert(getPortfolioUseCase.getPortfolio());
    }

    @PatchMapping(value = "stocks/{id}")
    public StockDto updateStocksGrade(@PathVariable Long id, @RequestBody StockDto stockDto) {
        stockDto.setId(id);
        return StockDtoAdapter
                .INSTACE
                .convert(updateStockGradeUseCase.execute(StockAdapter.INSTACE.convert(stockDto)));
    }

}
