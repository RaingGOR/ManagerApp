package raingor.ru.managerapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import raingor.ru.managerapp.client.ProductsRestClient;
import raingor.ru.managerapp.dto.CreatedProductDto;

@Controller
@RequestMapping("catalogue/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsRestClient productsRestClient;

    @GetMapping("list")
    public String getProductsPage(Model model) {
        model.addAttribute("products", this.productsRestClient.getAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage(Model model) {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String saveNewProduct(CreatedProductDto product) {
        this.productsRestClient.createProduct(product.name(), product.description());
        return "redirect:/catalogue/products/list";
    }

}

