package br.com.adrianorodrigues.stocksportfolio.entrypoint.rest;

import br.com.adrianorodrigues.stocksportfolio.domain.Portfolio;
import br.com.adrianorodrigues.stocksportfolio.domain.Stock;
import br.com.adrianorodrigues.stocksportfolio.exceptions.PortfolioNotFoundException;
import br.com.adrianorodrigues.stocksportfolio.usecase.GetPortfolioUseCase;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PortfolioControllerTest {

    @MockBean
    GetPortfolioUseCase getPortfolioUseCase;
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getPortfolioWhenContainsStocksShouldReturnData() {

        Mockito
                .when(getPortfolioUseCase.getPortfolio())
                .thenReturn(buildPortfolio());

        given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjF9.8lSCknTnRANlJ0AVzCgO2yF838WYA7bLaAR7vAKnofo")
                .get("/portfolio")
                .then()
                .statusCode(200)
                .body("stocks[0].id", equalTo(1))
                .body("stocks[0].ticker", equalTo("B3SA3"))
                .body("stocks[0].price", equalTo(10))
                .log()
                .all();
    }

    @Test
    void getPortfolioWhenFoundShouldReturnPortfolioData() {

        Mockito
                .when(getPortfolioUseCase.getPortfolio())
                .thenReturn(buildPortfolio());

        given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjF9.8lSCknTnRANlJ0AVzCgO2yF838WYA7bLaAR7vAKnofo")
                .get("/portfolio")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("stocks", hasSize(2))
                .log()
                .all();
    }

    @Test
    void getPortfolioWhenHasNoStocksShouldReturnEmptyList() {

        Mockito
                .when(getPortfolioUseCase.getPortfolio())
                .thenReturn(buildEmptyPortfolio());

        given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjF9.8lSCknTnRANlJ0AVzCgO2yF838WYA7bLaAR7vAKnofo")
                .get("/portfolio")
                .then()
                .statusCode(200)
                .body("stocks", empty())
                .log()
                .all();
    }

    @Test
    void getPortfolioWhenNotFoundShouldReturnErrorResponse() {

        Mockito
                .when(getPortfolioUseCase.getPortfolio())
                .thenThrow(new PortfolioNotFoundException("Portfolio not found"));

        given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjF9.8lSCknTnRANlJ0AVzCgO2yF838WYA7bLaAR7vAKnofo")
                .get("/portfolio")
                .then()
                .statusCode(404)
                .body("message", equalTo("Portfolio not found"))
                .body("status", equalTo(404))
                .log()
                .all();
    }

    @Test
    void getPortfolioWhenInvalidTokenShouldReturnErrorResponse() {

        Mockito
                .when(getPortfolioUseCase.getPortfolio())
                .thenThrow(new PortfolioNotFoundException("Portfolio not found"));

        given()
                .header("Authorization", "Bearer asd")
                .get("/portfolio")
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid token"))
                .body("status", equalTo(400))
                .log()
                .all();
    }

    private Portfolio buildEmptyPortfolio() {
        return Portfolio
                .builder()
                .id(1l)
                .stocks(Collections.emptyList())
                .build();
    }

    private Portfolio buildPortfolio() {
        return Portfolio
                .builder()
                .id(1l)
                .stocks(Arrays.asList(buildB3SA3(), buildBCFF11()))
                .build();
    }

    private Stock buildBCFF11() {
        return Stock
                .builder()
                .id(2l)
                .ticker("BCFF11")
                .price(BigDecimal.valueOf(100))
                .build();
    }

    private Stock buildB3SA3() {
        return Stock
                .builder()
                .id(1l)
                .ticker("B3SA3")
                .price(BigDecimal.valueOf(10))
                .build();
    }
}