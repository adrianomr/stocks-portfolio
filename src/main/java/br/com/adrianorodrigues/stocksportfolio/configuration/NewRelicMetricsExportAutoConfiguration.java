package br.com.adrianorodrigues.stocksportfolio.configuration;

import com.newrelic.telemetry.Attributes;
import io.micrometer.NewRelicRegistryConfig;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import io.micrometer.newrelic.NewRelicRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
@AutoConfigureBefore({
        CompositeMeterRegistryAutoConfiguration.class,
        SimpleMetricsExportAutoConfiguration.class
})
@AutoConfigureAfter(MetricsAutoConfiguration.class)
@ConditionalOnClass(NewRelicRegistry.class)
public class NewRelicMetricsExportAutoConfiguration {

    @Bean
    public NewRelicRegistryConfig newRelicConfig() {
        return new NewRelicRegistryConfig() {
            @Override
            public String get(String key) {
                return null;
            }

            @Value("${observability.newrelic.apiKey}")
            String apiKey;

            @Override
            public String apiKey() {
                return apiKey;
            }

            @Value("${observability.newrelic.uri}")
            String uri;

            @Override
            public String uri() {
                return uri;
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(5);
            }

            @Value("${spring.application.name}")
            String serviceName;

            @Override
            public String serviceName() {
                return serviceName;
            }

        };
    }

    @Bean
    public NewRelicRegistry newRelicMeterRegistry(NewRelicRegistryConfig config)
            throws UnknownHostException {
        NewRelicRegistry newRelicRegistry =
                NewRelicRegistry.builder(config)
                        .commonAttributes(
                                new Attributes()
                                        .put("host", InetAddress.getLocalHost().getHostName()))
                        .build();
        newRelicRegistry.start(new NamedThreadFactory("newrelic.micrometer.registry"));
        return newRelicRegistry;
    }
}