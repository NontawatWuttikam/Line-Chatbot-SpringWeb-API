package com.cpe.giftshoplineapi.service;

import com.cpe.giftshoplineapi.Repository.ProductRepository;
import com.model.Product;
import com.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ProductMessageService {

    @Autowired
    ProductRepository productRepository;

    public String getAllProductLineMessage() throws ExecutionException, InterruptedException {
        String msg = "รายชื่อสินค้าของร้านทั้งหมด\n";
        List<ProductInfo> productList;
        List<String> productStr;
        productList = this.getAllProductList();
        productStr = productList.stream().map( o-> {
             String productstr = o.getName();
             if (o.getQuantity() == 0)
                productstr = o.getName() + " (หมด)";
            return productstr;}).collect(Collectors.toList());


        int i = 1;
        for (String productString : productStr) {
            msg += i + ". " + productString + "\n";
            i++;
        }

        msg += "กรุณาพิมพ์หมายเลขด้านหน้าสินค้าเพื่อดูข้อมูล";
        return msg;
    }

    public List<ProductInfo> getAllProductList() throws ExecutionException, InterruptedException {
        List<ProductInfo> productList;
        productList = productRepository.getProductInfo();
        return productList;
    }

    public String getSpecificProduct(Integer index) throws ExecutionException, InterruptedException {
        String productInfo = "";
        List<ProductInfo> productList = getAllProductList();
        if (index > productList.size())
            return "ไม่พบสินค้าที่คุณร้องขอ กรุณาเลือกใหม่อีกครั้ง";

        ProductInfo product = productList.get(index-1);
        productInfo = product.getName() + "\n\n" +
                      "จำนวนในคลัง : " + product.getQuantity() +"\n\n" +
                      "Store Page : " + product.getStoreURL();
        return productInfo;

    }

}
