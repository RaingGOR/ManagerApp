package raingor.ru.catalogueservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raingor.ru.catalogueservice.domain.Product;
import raingor.ru.catalogueservice.dto.UpdateProductDTO;
import raingor.ru.catalogueservice.repositories.ProductsRepository;

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

    @Override
    public Product createProduct(String name, String description) {
        Product product = new Product(name, description);
        repository.save(product);
        return product;
    }

    @Override
    public Product getProduct(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product updateProduct(Long id, UpdateProductDTO updateProductDTO) {
        Product updatedProduct = repository.findById(id).orElseThrow(()
                -> new RuntimeException("Product not found"));
        updatedProduct.setName(updateProductDTO.name());
        updatedProduct.setDescription(updateProductDTO.description());
        repository.save(updatedProduct);

        return updatedProduct;
    }

    @Override
    public void deleteProduct(long id) {
        repository.deleteById(id);
    }
}
