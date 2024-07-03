package raingor.ru.managerapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raingor.ru.managerapp.repositories.ProductsRepository;

@RestController
@RequiredArgsConstructor
public class Test{   
    private final ProductsRepository productsRepository;

    @GetMapping
    public ResponseEntity<?> test() {

        return new ResponseEntity<>(productsRepository.findAll(), HttpStatus.OK);
    }
}
