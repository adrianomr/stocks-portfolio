package br.com.adrianorodrigues.stocksportfolio.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;

@Configuration
@Profile("dev")
public class WiremockStubsConfig {

    private final WireMockServer wireMockServer = new WireMockServer(8089);
    @Bean
    WireMockServer createWiremockServer(){
        wireMockServer.start();
        wireMockServer.stubFor(get("/stocks/1")
                .willReturn(ok("{\"price\": 100}")
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withFixedDelay(1000)
                )
        );
        return wireMockServer;
    }

}
