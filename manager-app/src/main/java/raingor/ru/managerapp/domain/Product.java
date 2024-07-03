package raingor.ru.managerapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "products")
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
