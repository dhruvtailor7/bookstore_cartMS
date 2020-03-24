package com.coviam.bookstore.cartMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartListDTO {
    private String quantity;
    private String productName;
    private String author;
    private String price;
    private String url;
    private String productId;
    private String merchantId;
    private String orderId;




}
