package pe.borabora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.borabora.dto.ProductDTO;
import pe.borabora.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //lista todos los productos
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //lista los productos segun la categoria
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Integer categoryId) {
        if (categoryId != null) {
            List<ProductDTO> products = productService.getProductsByCategoryId(categoryId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //lista los 10 productos màs vendidos (RANKING)
    @GetMapping("/topSelling")
    public ResponseEntity<List<ProductDTO>> getTopSellingProducts(@RequestParam(defaultValue = "10") int limit) {
        List<ProductDTO> topSellingProducts = productService.getTopSellingProducts(limit);
        return new ResponseEntity<>(topSellingProducts, HttpStatus.OK);
    }
}
