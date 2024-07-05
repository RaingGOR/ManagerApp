package raingor.ru.managerapp.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import raingor.ru.managerapp.client.exceptions.BadRequestException;
import raingor.ru.managerapp.dto.CreatedProductDto;
import raingor.ru.managerapp.dto.FullProductDTO;
import raingor.ru.managerapp.dto.UpdateProductDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductsRestClientImpl implements ProductsRestClient {
    private final RestClient restClient;
    private static final ParameterizedTypeReference<List<FullProductDTO>> PRODUCTE_TYPE_REFERENCE
            = new ParameterizedTypeReference<>() {
    };


    @Override
    public List<FullProductDTO> getAllProducts() {
        return
                this.restClient
                        .get()
                        .uri("/api/products")
                        .retrieve()
                        .body(PRODUCTE_TYPE_REFERENCE);
    }

    @Override
    public FullProductDTO createProduct(String productName, String productDescription) {
        try {
            return this.restClient
                    .post()
                    .uri("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new CreatedProductDto(productName, productDescription))
                    .retrieve()
                    .body(FullProductDTO.class);
        } catch (HttpClientErrorException.BadRequest e) {
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException(String.valueOf(problemDetail.getProperties().get("errors")));

        }
    }

    @Override
    public Optional<FullProductDTO> getProduct(Long productID) {
        try {
            return Optional.ofNullable(this.restClient
                    .get()
                    .uri("/api/products/{id}", productID)
                    .retrieve()
                    .body(FullProductDTO.class)
            );
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteProduct(Long productID) {
        try {
            Optional.ofNullable(this.restClient
                    .delete()
                    .uri("/api/products/{productID}", productID)
                    .retrieve()
                    .body(FullProductDTO.class)
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new NoSuchElementException(e);
        }
    }

    @Override
    public void updateProduct(Long productID, String productName, String productDescription) {
        try {
            this.restClient
                    .patch()
                    .uri("/api/products/{productID}", productID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateProductDTO(productName, productDescription))
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest e) {
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException(String.valueOf(problemDetail.getProperties().get("errors")));

        }
    }
}
