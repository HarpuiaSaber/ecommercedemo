package com.toan.ecommercedemo.entities;

import com.toan.ecommercedemo.enums.ProductStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
public class Product extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "unit_price_usd")
    private Double unitPriceUSD;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "description", length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Specification> specifications;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPriceUSD() {
        return unitPriceUSD;
    }

    public void setUnitPriceUSD(Double unitPriceUSD) {
        this.unitPriceUSD = unitPriceUSD;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
