package br.com.adrianorodrigues.stocksportfolio.e2e;

import br.com.adrianorodrigues.stocksportfolio.external.repository.StocksPortfolioRepository;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StockDto;
import br.com.adrianorodrigues.stocksportfolio.external.repository.dto.StocksPortfolioDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UpdateStockGradeTest {

    @LocalServerPort
    int port;

    @Autowired
    StocksPortfolioRepository repository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        repository.deleteAll();
    }

    @Test
    void updateStockGrade() {
        StocksPortfolioDto portfolio = buildPortfolio();
        portfolio.getStocks().forEach(stockDto -> stockDto.setPortfolio(portfolio));
        repository.save(portfolio);

        given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjF9.8lSCknTnRANlJ0AVzCgO2yF838WYA7bLaAR7vAKnofo")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"grade\": 5.97}")
                .patch("/portfolio/stocks/" + portfolio.getStocks().get(0).getId())
                .then()
                .statusCode(200)
                .log()
                .all();

        StocksPortfolioDto stocksPortfolioDto = repository
                .findByUserId(portfolio.getUserId())
                .orElse(null);

        StockDto stockDto = stocksPortfolioDto
                .getStocks()
                .stream()
                .filter(stockDto1 -> stockDto1.getId().equals(portfolio.getStocks().get(0).getId()))
                .findAny()
                .orElse(null);

        assertEquals(new BigDecimal("10.00"), stockDto.getPriceAvg());
        assertEquals(BigDecimal.valueOf(5.97), stockDto.getGrade());
    }

    private StocksPortfolioDto buildPortfolio() {
        return StocksPortfolioDto
                .builder()
                .stocks(Arrays.asList(buildB3SA3(), buildBCFF11()))
                .userId(1l)
                .build();
    }

    private StockDto buildBCFF11() {
        return StockDto
                .builder()
                .ticker("BCFF11")
                .priceAvg(BigDecimal.valueOf(10))
                .build();
    }

    private StockDto buildB3SA3() {
        return StockDto
                .builder()
                .ticker("B3SA3")
                .priceAvg(BigDecimal.valueOf(10))
                .build();
    }

}