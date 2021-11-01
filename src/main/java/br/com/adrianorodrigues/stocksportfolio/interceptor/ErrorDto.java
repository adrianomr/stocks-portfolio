package br.com.adrianorodrigues.stocksportfolio.interceptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorDto {

    private int status;
    private String message;

}
