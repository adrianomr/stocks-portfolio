package br.com.adrianorodrigues.stocksportfolio.exceptions;

import org.springframework.http.HttpStatus;

public class PortfolioNotFoundException extends BaseException{

    public PortfolioNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
