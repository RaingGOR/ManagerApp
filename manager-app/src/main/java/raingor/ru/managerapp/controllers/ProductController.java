package raingor.ru.managerapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import raingor.ru.managerapp.domain.Product;
import raingor.ru.managerapp.dto.UpdateProductDTO;
import raingor.ru.managerapp.service.DefaultProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products/{id}")
public class ProductController {
    private final DefaultProductService productsService;

    @ModelAttribute("product")
    public Product product(@PathVariable("id") long id) {
        return this.productsService.getProduct(id);
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
    public String saveProduct(@ModelAttribute("product") Product product, UpdateProductDTO updateProductDTO) {
        this.productsService.updateProduct(product, updateProductDTO);
        return "redirect:/catalogue/products/list";
    }

    @DeleteMapping
    public String deleteProduct(@PathVariable("id") long id) {
        this.productsService.deleteProduct(id);
        return "redirect:/catalogue/products/list";
    }

}
