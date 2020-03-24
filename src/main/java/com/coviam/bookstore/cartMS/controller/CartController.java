package com.coviam.bookstore.cartMS.controller;

import com.coviam.bookstore.cartMS.dto.CartDTO;
import com.coviam.bookstore.cartMS.dto.CartListDTO;
import com.coviam.bookstore.cartMS.dto.CartUpdateDTO;
import com.coviam.bookstore.cartMS.dto.RemoveAllDTO;
import com.coviam.bookstore.cartMS.entity.Cart;
import com.coviam.bookstore.cartMS.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/addToCart")
    ResponseEntity<String> add(@RequestBody CartDTO cartDTO){
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDTO,cart);
        if(cart.getQuantity().equals("0")){
            cartService.remove(cart);
        }
        else{
            System.out.println("cart"+cart);
            System.out.println("cartDTO"+cartDTO);
            cartService.add(cart);}
        return new ResponseEntity<String>("Success",HttpStatus.OK);
    }

    @DeleteMapping("/removeFromCart")
    ResponseEntity<String> remove(@RequestBody CartDTO cartDTO){
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDTO,cart);
        cartService.remove(cart);
        return new ResponseEntity<String>("Success",HttpStatus.OK);
    }

    @DeleteMapping("/removeAllFromCart")
    ResponseEntity<String> removeAll(@RequestBody RemoveAllDTO removeAllDTO){
        cartService.removeAll(removeAllDTO.getCartId());
        return new ResponseEntity<String>("Success",HttpStatus.OK);
    }

    @GetMapping("/getFromCart/{id}")
    List<CartListDTO> getByCartId(@PathVariable("id") String cartId){
        return cartService.getByCartId(cartId);

    }


    @PostMapping("/updateGuestCart")
    void updateGuestCart(@RequestBody CartUpdateDTO cartUpdateDTO){
        String cartId=cartUpdateDTO.getCartId();
        String customerId=cartUpdateDTO.getCustomerId();
        cartService.updateGuestCart(cartId,customerId);
    }


}
