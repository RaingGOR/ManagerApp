package raingor.ru.managerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raingor.ru.managerapp.domain.Product;
import raingor.ru.managerapp.repositories.ProductsRepository;

import java.util.Collections;
import java.util.List;

@Service
public class DefaultProductService implements ProductsService {
    private final ProductsRepository repository;

    @Autowired
    public DefaultProductService(ProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(this.repository.findAll());
    }
}
