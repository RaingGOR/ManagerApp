package raingor.ru.managerapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import raingor.ru.managerapp.repositories.ProductsRepository;
import raingor.ru.managerapp.service.DefaultProductService;

@Controller
@RequestMapping("catalogue/products")
@RequiredArgsConstructor
public class ProductsController {
    private final DefaultProductService productsService;
    private final ProductsRepository productsRepository;

    @GetMapping
    public String getProductsPage(Model model) {
        model.addAttribute("products", this.productsService.getAllProducts());
        return "catalogue/products/list";
    }

}

