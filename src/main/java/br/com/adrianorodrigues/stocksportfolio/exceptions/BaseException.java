package br.com.adrianorodrigues.stocksportfolio.exceptions;

import br.com.adrianorodrigues.stocksportfolio.interceptor.ErrorDto;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class BaseException extends RuntimeException{

    private final HttpStatus statusCode;

    protected BaseException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    protected BaseException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public ErrorDto toErrorDto(){
        return ErrorDto.builder().status(getStatusCode().value()).message(getMessage()).build();
    }
}
