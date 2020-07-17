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

}
