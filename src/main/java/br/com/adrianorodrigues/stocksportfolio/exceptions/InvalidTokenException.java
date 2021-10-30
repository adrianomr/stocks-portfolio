package br.com.adrianorodrigues.stocksportfolio.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BaseException {

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause, HttpStatus.BAD_REQUEST);
    }

}
