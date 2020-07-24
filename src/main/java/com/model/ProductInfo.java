package com.model;


import com.sun.org.apache.xpath.internal.operations.Bool;

public class ProductInfo {

    private String name;
    private Long quantity;
    private String storeURL;
    private Boolean isPromotion;
    private String promotionInfo;
    private String imageURL;
    private String description;

    public ProductInfo(String name, Long quantity,String storeURL, String imageURL,String description) {
        this.name = name;
        this.quantity = quantity;
        this.storeURL = storeURL;
        this.imageURL = imageURL;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getStoreURL() {
        return storeURL;
    }

    public void setStoreURL(String storeURL) {
        this.storeURL = storeURL;
    }

    public Boolean getPromotion() {
        return isPromotion;
    }

    public void setPromotion(Boolean promotion) {
        isPromotion = promotion;
    }

    public String getPromotionInfo() {
        return promotionInfo;
    }

    public void setPromotionInfo(String promotionInfo) {
        this.promotionInfo = promotionInfo;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
