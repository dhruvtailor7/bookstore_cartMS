package com.coviam.bookstore.cartMS.client;

import com.coviam.bookstore.cartMS.dto.QuantityDTO;
import com.coviam.bookstore.cartMS.dto.RemoveProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("http://bookstore-merchant.herokuapp.com/")
public interface MerchantClient {
    @PostMapping("/getQuantity")
    public String getQuantity(@RequestBody QuantityDTO quantityDTO);

    @PostMapping("/getPrice")
    public String getPrice(@RequestBody RemoveProductDTO removeProductDTO);
}
