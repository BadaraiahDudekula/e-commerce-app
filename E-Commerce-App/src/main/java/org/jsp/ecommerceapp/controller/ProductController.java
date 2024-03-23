package org.jsp.ecommerceapp.controller;

import java.util.List;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping("/{merchant_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,
			@PathVariable int merchant_id) {
		return productService.saveProduct(product, merchant_id);
	}

	@GetMapping("/find-by-merchant-id/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(@PathVariable int merchant_id) {
		return productService.findProductsByMerchantId(merchant_id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return productService.findAll();
	}

	@GetMapping("/find-by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable String brand) {
		return productService.findByBrand(brand);
	}

	@GetMapping("/find-by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String category) {
		return productService.findByCategory(category);
	}

	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable int id) {
		return productService.findById(id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int id) {
		return productService.findById(id);
	}
	
	@PutMapping("/{user_id}/{product_id}")
	public ResponseEntity<ResponseStructure<String>> addToCart(@PathVariable int user_id,@PathVariable int product_id) {
	return productService.addToCart(user_id, product_id);
	}
	@PutMapping("/wishlist/{user_id}/{product_id}")
	public ResponseEntity<ResponseStructure<String>> addToWishList(@PathVariable int user_id,@PathVariable int product_id) {
	return productService.addToWishList(user_id, product_id);
	}

}
