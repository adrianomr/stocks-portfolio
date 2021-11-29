package br.com.adrianorodrigues.stocksportfolio.adapter.external.repository;

import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockDtoAdapterHelper {

    StockDtoAdapterHelper INSTACE = Mappers.getMapper(StockDtoAdapterHelper.class);

    StockDto convert(Stock stock);
}
