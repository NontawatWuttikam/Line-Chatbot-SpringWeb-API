package com.model;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT")
public class Product {

    @Id
    @Column(name="PRODUCTID")
    private Long productId;

    @Column(name="PRODUCTNAME")
    private String productName;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="QUANTITY")
    private Integer quantity;

    @Column(name="STOREPAGEURL")
    private String storePageUrl;

    @Column(name = "IMAGEURL")
    private String imageURL;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStorePageUrl() {
        return storePageUrl;
    }

    public void setStorePageUrl(String storePageUrl) {
        this.storePageUrl = storePageUrl;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
