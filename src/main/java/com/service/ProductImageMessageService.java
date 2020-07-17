package com.service;

import com.linecorp.bot.model.message.ImageMessage;
import com.model.Product;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImageMessageService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMessageService productMessageService;

    public Optional<ImageMessage> getImageMessageByProduct(Integer index) {
        List<Product> productList = productMessageService.getAllProductList();

        if (index > productList.size())
            return Optional.empty();

        Product product = productList.get(index - 1);
        if (product.getImageURL() == null)
            return Optional.empty();

        URI imageUri = URI.create(product.getImageURL());
        ImageMessage imageMessage = new ImageMessage(imageUri,imageUri);

        return Optional.of(imageMessage);
    }
}
