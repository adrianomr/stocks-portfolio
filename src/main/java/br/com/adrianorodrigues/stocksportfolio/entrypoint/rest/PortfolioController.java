package br.com.adrianorodrigues.stocksportfolio.entrypoint.rest;

import br.com.adrianorodrigues.stocksportfolio.adapter.entrypoint.rest.PortfolioDtoAdapter;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.PortfolioDto;
import br.com.adrianorodrigues.stocksportfolio.usecase.GetPortfolioUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("portfolio")
public class PortfolioController {

    @Autowired
    GetPortfolioUseCase getPortfolioUseCase;

    @GetMapping
    public PortfolioDto getPortfolio(){
        return PortfolioDtoAdapter
                .INSTACE
                .convert(getPortfolioUseCase.getPortfolio());
    }

}
