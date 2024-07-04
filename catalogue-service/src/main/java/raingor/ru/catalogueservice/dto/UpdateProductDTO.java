package raingor.ru.catalogueservice.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateProductDTO(
        @NotNull
        String name,
        String description
) {
}
