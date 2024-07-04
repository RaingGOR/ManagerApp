package raingor.ru.managerapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import raingor.ru.managerapp.client.ProductsRestClientImpl;

@Configuration
public class ClientBeans {
    @Bean
    ProductsRestClientImpl restClient(@Value("${raingor.services.catalogue.uri:http://localhost:8081}")
                                      String catalogueBaseUrl) {

        return new ProductsRestClientImpl(RestClient
                .builder()
                .baseUrl(catalogueBaseUrl)
                .build()
        );
    }
}
