package raingor.ru.managerapp.client;

import raingor.ru.managerapp.dto.FullProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {
    List<FullProductDTO> getAllProducts();
    FullProductDTO createProduct(String productName, String productDescription);
    Optional<FullProductDTO> getProduct(Long productID);
    void deleteProduct(Long productID);
    void updateProduct(Long productID, String productName, String productDescription);
}
