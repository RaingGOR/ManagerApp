package raingor.ru.managerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raingor.ru.managerapp.domain.Product;
import raingor.ru.managerapp.dto.UpdateProductDTO;
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

    @Override
    public void createProduct(String name, String description) {
        repository.save(new Product(name, description));
    }

    @Override
    public Product getProduct(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product updateProduct(Product product, UpdateProductDTO updateProductDTO) {
        Product updatedProduct = repository.findById(product.getId()).orElseThrow(()
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
