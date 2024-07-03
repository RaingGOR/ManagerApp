package raingor.ru.managerapp.service;

import org.springframework.stereotype.Service;
import raingor.ru.managerapp.domain.Product;

import java.util.List;

@Service
public interface ProductsService {
    List<Product> getAllProducts();

    void createProduct(String name, String description);
}
