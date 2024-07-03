package raingor.ru.managerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raingor.ru.managerapp.domain.Product;


@Repository
public interface ProductsRepository  extends JpaRepository<Product, Long> {
}
