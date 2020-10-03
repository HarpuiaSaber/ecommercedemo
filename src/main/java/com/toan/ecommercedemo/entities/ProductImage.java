package com.toan.ecommercedemo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_image")
public class ProductImage extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path")
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "from_tiki")
    private Boolean fromTiki;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getFromTiki() {
        return fromTiki;
    }

    public void setFromTiki(Boolean fromTiki) {
        this.fromTiki = fromTiki;
    }
}
