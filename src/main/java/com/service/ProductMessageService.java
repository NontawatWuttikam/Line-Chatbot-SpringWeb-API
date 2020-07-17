package com.service;

import com.model.Product;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMessageService {

    @Autowired
    ProductRepository productRepository;

    public String getAllProductLineMessage() {
        String msg = "รายชื่อสินค้าของร้านทั้งหมด\n";
        List<Product> productList;
        List<String> productStr;
        productList = this.getAllProductList();
        productStr = productList.stream().map( o-> {
             String productstr = o.getProductName();
             if (o.getQuantity() == 0)
                productstr = o.getProductName() + "(หมด)";
            return productstr;}).collect(Collectors.toList());

        for (String productString : productStr) {
            msg += productString + "\n";
        }

        msg = "กรุณาพิมพ์หมายเลขด้านหน้าสินค้าเพื่อดูข้อมูล";
        return msg;
    }

    public List<Product> getAllProductList() {
        List<Product> productList;
        productList = productRepository.findAll();
        return productList;
    }

    public String getSpecificProduct(Integer index) {
        String productInfo = "";
        List<Product> productList = getAllProductList();
        if (index > productList.size())
            return "ไม่พบสินค้าที่คุณร้องขอ กรุณาเลือกใหม่อีกครั้ง";

        Product product = productList.get(index-1);
        String productDescription = (product.getDescription() != null)? product.getDescription() : "";
        productInfo = product.getProductName() + "\n\n" +
                      productDescription + "\n\n" +
                      "จำนวนในคลัง : " + product.getQuantity();
        return productInfo;

    }

}
