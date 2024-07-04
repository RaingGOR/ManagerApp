package raingor.ru.catalogueservice.dto;

import jakarta.validation.constraints.NotNull;

public record CreatedProductDto(
        @NotNull
        String name,
        String description
) {
}
