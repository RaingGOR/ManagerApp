package raingor.ru.catalogueservice.service;

import org.springframework.stereotype.Service;
import raingor.ru.catalogueservice.domain.Product;
import raingor.ru.catalogueservice.dto.UpdateProductDTO;

import java.util.List;

@Service
public interface ProductsService {
    List<Product> getAllProducts();

    Product createProduct(String name, String description);

    Product getProduct(long id);

    Product updateProduct(Long id, UpdateProductDTO updateProductDTO);

    void deleteProduct(long id);
}
