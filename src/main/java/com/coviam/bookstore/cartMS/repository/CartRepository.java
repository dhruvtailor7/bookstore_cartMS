package com.coviam.bookstore.cartMS.repository;

import com.coviam.bookstore.cartMS.entity.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends CrudRepository<Cart,String> {
    void deleteByCartId(String cartId);

    @Transactional
    @Modifying
    @Query(value = "Update cart set cart_id=?2 where cart_id=?1",nativeQuery = true)
    void updateCart(String cartId, String customerId);
}
