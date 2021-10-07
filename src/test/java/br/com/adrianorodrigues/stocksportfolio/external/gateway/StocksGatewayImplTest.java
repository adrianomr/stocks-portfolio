package br.com.adrianorodrigues.stocksportfolio.external.gateway;

import br.com.adrianorodrigues.stocksportfolio.external.gateway.dto.StockDto;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 8089)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class StocksGatewayImplTest {

    @Autowired StocksGateway stocksGateway;

    @Test
    void getStocks() {
        stubFor(get("/stocks/1").willReturn(ok("{}").withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));

        Mono<List<StockDto>> stocks = stocksGateway.getStocks(Collections.singletonList("1"));

        assertEquals(Collections.singletonList(StockDto.builder().build()), stocks.block());
    }
}