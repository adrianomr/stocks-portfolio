package br.com.adrianorodrigues.stocksportfolio.external.gateway.reactive;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class ReactiveWebClientImpl implements ReactiveWebClient {

    private WebClient webClient;
    private HttpClient httpClient;

    public ReactiveWebClientImpl(String baseUrl) {
        buildHttpClient();
        buildWebClient(baseUrl);
    }

    private void buildWebClient(String baseUrl) {
        webClient = WebClient
                .builder()
                .clientConnector(new JettyClientHttpConnector(httpClient))
                .baseUrl(baseUrl)
                .build();
    }

    private void buildHttpClient() {
        SslContextFactory.Client sslContextFactory = new SslContextFactory.Client();
        httpClient = new HttpClient(sslContextFactory){
            @Override
            public Request newRequest(URI uri) {
                return enhance(super.newRequest(uri));
            }
        };

    }

    Request enhance(Request request) {
        StringBuilder groupResponse = new StringBuilder("RESPONSE");
        StringBuilder groupRequest = new StringBuilder("REQUEST");


        StopWatch timer = new StopWatch();
        request.onRequestBegin(theRequest -> {
            timer.start();
            groupRequest.append(String.format(" Method: %s, URL: %s, Headers: %s, Params: %s",
                    theRequest.getMethod(),
                    theRequest.getURI(),
                    formatHeaders(theRequest.getHeaders()),
                    formatParams(theRequest.getParams())));
        });
        request.onRequestContent((theRequest, content) -> {
            groupRequest.append(String.format(", Body: %s", contentToString(content)));
        });
        request.onRequestSuccess(theRequest -> {
            log.trace(groupRequest.toString());
        });

        request.onResponseBegin(theResponse -> {
            timer.stop();
            groupResponse.append(String.format(" Method: %s, URL: %s, Status: %s, Elapsed time: %sms",
                    theResponse.getRequest().getMethod(),
                    theResponse.getRequest().getURI(),
                    theResponse.getStatus(),
                    timer.getTotalTimeMillis()));
        });
        request.onResponseContent((theResponse, content) -> {
            groupResponse.append(String.format(", Body: %s", contentToString(content)));
        });
        request.onResponseSuccess(theResponse -> {
            log.trace(groupResponse.toString());
        });
        return request;
    }

    private String formatHeaders(HttpFields httpFields) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(httpFields.stream().map(httpField -> {
            StringBuilder sbField = new StringBuilder();
            sbField.append(httpField.getName());
            sbField.append("=[");
            sbField.append(StringUtils.join(Arrays.asList(httpField.getValues()), ','));
            sbField.append("]");
            return sbField.toString();
        }).collect(Collectors.joining(",")));

        sb.append("}");

        return sb.toString();
    }

    private String formatParams(Fields format) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(format.getNames().stream().map(name -> {
            StringBuilder sbField = new StringBuilder();
            sbField.append(name);
            sbField.append("=[");
            sbField.append(StringUtils.join(format.get(name).getValues(), ','));
            sbField.append("]");
            return sbField.toString();
        }).collect(Collectors.joining(",")));
        sb.append("}");

        return sb.toString();
    }

    private String contentToString(ByteBuffer byteBuffers) {
        try {
            String content = new String(byteBuffers.array()).replaceAll("\r\n|\r|\n|\t", "");
            int index = content.lastIndexOf("}");
            return content.substring(0, index + 1);
        } catch (Exception e) {
            log.error("Unable to convert body", e);
        }
        return "";
    }

    public WebClient getWebClient() {
        return webClient;
    }

    @Override
    public RequestHeadersUriSpec<?> get() {
        return webClient.get();
    }

    @Override
    public RequestHeadersUriSpec<?> head() {
        return webClient.head();
    }

    @Override
    public RequestBodyUriSpec post() {
        return webClient.post();
    }

    @Override
    public RequestBodyUriSpec put() {
        return webClient.put();
    }

    @Override
    public RequestBodyUriSpec patch() {
        return webClient.patch();
    }

    @Override
    public RequestHeadersUriSpec<?> delete() {
        return webClient.delete();
    }

    @Override
    public RequestHeadersUriSpec<?> options() {
        return webClient.options();
    }

    @Override
    public RequestBodyUriSpec method(HttpMethod method) {
        return webClient.method(method);
    }

    @Override
    public Builder mutate() {
        return webClient.mutate();
    }
}
