package br.com.adrianorodrigues.stocksportfolio.adapter.entrypoint.rest;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.PortfolioDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PortfolioDtoAdapter {

    PortfolioDtoAdapter INSTACE = Mappers.getMapper(PortfolioDtoAdapter.class);

    PortfolioDto convert(Portfolio portfolio);
}
