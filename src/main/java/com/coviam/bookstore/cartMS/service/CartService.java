package com.coviam.bookstore.cartMS.service;

import com.coviam.bookstore.cartMS.dto.CartListDTO;
import com.coviam.bookstore.cartMS.entity.Cart;

import java.util.List;

public interface CartService {

    Cart add(Cart cart);

    void remove(Cart cart);
    void removeAll(String cartId);

    List<CartListDTO> getByCartId(String cartId);

    void updateGuestCart(String cartId,String customerId);
}
