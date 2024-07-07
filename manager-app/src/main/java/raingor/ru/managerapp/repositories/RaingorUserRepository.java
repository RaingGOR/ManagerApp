package raingor.ru.managerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raingor.ru.managerapp.domain.RaingorUser;

import java.util.Optional;

@Repository
public interface RaingorUserRepository extends JpaRepository<RaingorUser, Long> {
    Optional<RaingorUser> findByName(String username);
}
