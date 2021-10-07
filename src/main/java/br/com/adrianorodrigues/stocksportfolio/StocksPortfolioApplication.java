package br.com.adrianorodrigues.stocksportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class StocksPortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksPortfolioApplication.class, args);
	}

}
