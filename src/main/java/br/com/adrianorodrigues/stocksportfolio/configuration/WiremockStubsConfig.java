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

    public static final String CONTENT_TYPE = "Content-Type";
    private final WireMockServer wireMockServer = new WireMockServer(8089);
    @Bean
    WireMockServer createWiremockServer(){
        wireMockServer.start();
        wireMockServer.stubFor(get("/stocks/BCFF11")
                .willReturn(ok("{\"price\": 100}")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withFixedDelay(1000)
                )
        );
        wireMockServer.stubFor(get("/stocks/B3SA3")
                .willReturn(ok("{\"price\": 50}")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withFixedDelay(1000)
                )
        );
        wireMockServer.stubFor(get("/stocks/HGRE11")
                .willReturn(ok("{\"price\": 10}")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withFixedDelay(1000)
                )
        );
        return wireMockServer;
    }

}
