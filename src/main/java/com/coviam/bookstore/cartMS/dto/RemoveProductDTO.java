package com.coviam.bookstore.cartMS.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveProductDTO {
    private String productId;
    private String merchantId;
}
