package raingor.ru.catalogueservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raingor.ru.catalogueservice.domain.Product;


@Repository
public interface ProductsRepository  extends JpaRepository<Product, Long> {
}
