package br.com.adrianorodrigues.stocksportfolio.adapter.entrypoint.rest;

import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.StockDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockDtoAdapter {

    StockDtoAdapter INSTACE = Mappers.getMapper(StockDtoAdapter.class);

    StockDto convert(Stock stockDto);
}
