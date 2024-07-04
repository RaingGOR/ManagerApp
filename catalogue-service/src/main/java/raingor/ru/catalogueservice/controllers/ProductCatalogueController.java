package raingor.ru.catalogueservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import raingor.ru.catalogueservice.domain.Product;
import raingor.ru.catalogueservice.dto.UpdateProductDTO;
import raingor.ru.catalogueservice.service.ProductsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/{id}")
public class ProductCatalogueController {
    private final ProductsService productsService;

    @ModelAttribute("product")
    public Product addAttributes(@PathVariable("id") Long id) {
        return this.productsService.getProduct(id);

    }

    @GetMapping
    public ResponseEntity<?> findProductCatalogue(@ModelAttribute("product") Product product) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateProduct(@Valid @RequestBody UpdateProductDTO updateProductDTO
            , @PathVariable("id") Long id, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw  exception;
            }else {
                throw new BindException(bindingResult);
            }
        }else {
            this.productsService.updateProduct(id, updateProductDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        this.productsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
