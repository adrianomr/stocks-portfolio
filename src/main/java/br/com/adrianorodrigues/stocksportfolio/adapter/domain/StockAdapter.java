package br.com.adrianorodrigues.stocksportfolio.adapter.domain;

import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.entrypoint.rest.dto.StockDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockAdapter {

    StockAdapter INSTACE = Mappers.getMapper(StockAdapter.class);

    Stock convert(StockDto stockDto);

    Stock convert(br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto stockDto);
}
