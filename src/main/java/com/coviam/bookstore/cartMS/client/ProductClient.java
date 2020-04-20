package com.coviam.bookstore.cartMS.client;

import com.coviam.bookstore.cartMS.dto.ProductDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("http://bookstore-product.herokuapp.com/")
public interface ProductClient {


    @GetMapping("/getGenreList")
    List<String> getGenreList();



    @PostMapping("/addProduct")
    String addProduct(@RequestBody ProductDetailsDTO productDetailsDTO);

    @GetMapping("/getProductByProductId/{id}")
    ProductDetailsDTO getProductById(@PathVariable("id") String id);


}
