package br.com.adrianorodrigues.stocksportfolio.adapter.domain;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.PortfolioDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PortfolioAdapter {

    PortfolioAdapter INSTACE = Mappers.getMapper(PortfolioAdapter.class);

    Portfolio convert(StocksPortfolioDto portfolioDto);
}
