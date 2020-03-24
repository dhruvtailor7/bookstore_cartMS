package com.coviam.bookstore.cartMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartUpdateDTO {
    private String cartId;
    private String customerId;
}
