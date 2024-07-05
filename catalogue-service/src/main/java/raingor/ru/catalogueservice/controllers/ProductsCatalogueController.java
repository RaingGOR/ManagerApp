package raingor.ru.catalogueservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import raingor.ru.catalogueservice.domain.Product;
import raingor.ru.catalogueservice.dto.CreatedProductDto;
import raingor.ru.catalogueservice.dto.FullProductDTO;
import raingor.ru.catalogueservice.service.ProductsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductsCatalogueController {
    private final ProductsService productsService;

    @GetMapping
    public List<FullProductDTO> getProducts() {
        return this.productsService.getAllProducts().stream().map(
                        m -> new FullProductDTO(m.getId(), m.getName(), m.getDescription()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody CreatedProductDto createdProductDto
            , BindingResult bindingResult, UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Product product = productsService.createProduct(createdProductDto.name(), createdProductDto.description());
            return ResponseEntity
                    .created(uriBuilder.replacePath("/api/products/{id}")
                            .buildAndExpand(product.getId()).toUri())
                    .body(product);
        }
    }
}
