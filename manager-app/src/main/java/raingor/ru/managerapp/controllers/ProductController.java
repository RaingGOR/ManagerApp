package raingor.ru.managerapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import raingor.ru.managerapp.client.ProductsRestClient;
import raingor.ru.managerapp.dto.FullProductDTO;
import raingor.ru.managerapp.dto.UpdateProductDTO;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products/{id}")
public class ProductController {
    private final ProductsRestClient restClient;

    @ModelAttribute("product")
    public Optional<FullProductDTO> product(@PathVariable("id") long id) {
        return this.restClient.getProduct(id);
    }

    @GetMapping
    public String getProductById() {
        return "catalogue/products/view";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalogue/products/edit";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") FullProductDTO product, UpdateProductDTO updateProductDTO) {
        this.restClient.updateProduct(product.id(), updateProductDTO.name(),updateProductDTO.description());
        return "redirect:/catalogue/products/list";
    }

    @DeleteMapping
    public String deleteProduct(@PathVariable("id") long id) {
        this.restClient.deleteProduct(id);
        return "redirect:/catalogue/products/list";
    }

}
