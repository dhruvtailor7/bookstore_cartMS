package com.coviam.bookstore.cartMS.service.Impl;

import com.coviam.bookstore.cartMS.client.MerchantClient;
import com.coviam.bookstore.cartMS.client.ProductClient;
import com.coviam.bookstore.cartMS.dto.CartListDTO;
import com.coviam.bookstore.cartMS.dto.ProductDetailsDTO;
import com.coviam.bookstore.cartMS.dto.QuantityDTO;
import com.coviam.bookstore.cartMS.dto.RemoveProductDTO;
import com.coviam.bookstore.cartMS.entity.Cart;
import com.coviam.bookstore.cartMS.repository.CartRepository;
import com.coviam.bookstore.cartMS.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartSercviceimpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductClient productClient;
    @Autowired
    MerchantClient merchantClient;

    @Override
    public void updateGuestCart(String cartId, String customerId) {
        cartRepository.updateCart(cartId,customerId);
    }

    @Override
    public Cart add(Cart cart) {
        List<Cart> list=(ArrayList<Cart>)cartRepository.findAll();
        QuantityDTO quantityDTO = new QuantityDTO();
        BeanUtils.copyProperties(cart,quantityDTO);
       // int quantity = Integer.parseInt(merchantClient.getQuantity(quantityDTO));
        list=list.stream().filter(c -> (cart.getCartId().equals(c.getCartId()) && cart.getMerchantId().equals(c.getMerchantId()) && cart.getProductId().equals(c.getProductId()))).collect(Collectors.toList());
        if(list.size()!=0)
         cart.set_id(list.get(0).get_id());

        return cartRepository.save(cart);
    }

    @Override
    public void remove(Cart cart) {
        ArrayList<Cart> cartList = (ArrayList<Cart>) cartRepository.findAll();
        String _id=cartList.stream().filter(cart1 -> (cart.getCartId().equals(cart1.getCartId()) && cart.getMerchantId().equals(cart1.getMerchantId()) && cart.getProductId().equals(cart1.getProductId()))).collect(Collectors.toList()).get(0).get_id();
        cart.set_id(_id);
        cartRepository.delete(cart);
    }

    @Override
    public void removeAll(String cartId) {
        List<Cart> cartList=(List<Cart>) cartRepository.findAll();
        cartList.stream().filter(cart -> cart.getCartId().equals(cartId)).forEach(cart -> {
            cartRepository.deleteByCartId(cart.getCartId());
        });
    }

    @Override
    public List<CartListDTO> getByCartId(String cartId) {
        List<Cart> cartList=(ArrayList<Cart>)cartRepository.findAll();

        cartList = cartList.stream().filter(cart -> cart.getCartId().equals(cartId)).collect(Collectors.toList());
        List<CartListDTO> cartItemsListDTO=new ArrayList<>();
        for(Cart cart:cartList){
            CartListDTO cartListDTO=new CartListDTO();
            BeanUtils.copyProperties(cart,cartListDTO);
            System.out.println("___________---------------"+cart.getProductId());
            ProductDetailsDTO productdetailsDTO=productClient.getProductById(cart.getProductId());
            RemoveProductDTO removeProductDTO=new RemoveProductDTO();
            removeProductDTO.setProductId(cart.getProductId());
            removeProductDTO.setMerchantId(cart.getMerchantId());
            String price = merchantClient.getPrice(removeProductDTO);
            BeanUtils.copyProperties(productdetailsDTO,cartListDTO);
            cartListDTO.setPrice(price);
            cartListDTO.setProductId(cart.getProductId());
            cartListDTO.setMerchantId(cart.getMerchantId());
            cartListDTO.setQuantity(cart.getQuantity());
            cartItemsListDTO.add(cartListDTO);
        }
        return cartItemsListDTO;
    }
}
