package br.com.adrianorodrigues.stocksportfolio.entrypoint.rest;

import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.PortfolioDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("portfolio")
public class PortfolioController {

    public PortfolioDto getPortfolio(){
        return PortfolioDto
                .builder()
                .build();
    }

}
