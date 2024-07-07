package raingor.ru.managerapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import raingor.ru.managerapp.client.ProductsRestClientImpl;

@Configuration
public class ClientBeans {
    @Bean
    ProductsRestClientImpl restClient(
            @Value("${services.catalogue.uri:http://localhost:8081}") String catalogueBaseUrl,
            @Value("${services.catalogue.username:catalogue_service_user}") String username,
            @Value("${services.catalogue.password:}") String password
                                      ) {

        return new ProductsRestClientImpl(RestClient
                .builder()
                .baseUrl(catalogueBaseUrl)
                .requestInterceptor(new BasicAuthenticationInterceptor(username,password))
                .build()
        );
    }
}
