package raingor.ru.managerapp.service;

import org.springframework.stereotype.Service;
import raingor.ru.managerapp.domain.Product;
import raingor.ru.managerapp.dto.UpdateProductDTO;

import java.util.List;

@Service
public interface ProductsService {
    List<Product> getAllProducts();

    void createProduct(String name, String description);

    Product getProduct(long id);

    Product updateProduct(Product product, UpdateProductDTO updateProductDTO);

    void deleteProduct(long id);
}
