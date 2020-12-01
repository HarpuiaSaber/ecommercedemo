package com.toan.ecommercedemo.entities;

import com.toan.ecommercedemo.enums.ProductStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_history")
public class ProductHistory extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;

    @Column(name = "content")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
