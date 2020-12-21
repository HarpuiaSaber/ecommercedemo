package com.toan.ecommercedemo.entities;

import com.toan.ecommercedemo.enums.CommentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 2000)
    private String content;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<CommentImage> images;

    @Column(name = "helpful_number")
    private Integer helpfulNumber;

    @Column(name = "not_helpful_number")
    private Integer notHelpfulNumber;

    public Comment() {
    }

    public Comment(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<CommentImage> getImages() {
        return images;
    }

    public void setImages(List<CommentImage> images) {
        this.images = images;
    }

    public Integer getHelpfulNumber() {
        return helpfulNumber;
    }

    public void setHelpfulNumber(Integer helpfulNumber) {
        this.helpfulNumber = helpfulNumber;
    }

    public Integer getNotHelpfulNumber() {
        return notHelpfulNumber;
    }

    public void setNotHelpfulNumber(Integer notHelpfulNumber) {
        this.notHelpfulNumber = notHelpfulNumber;
    }
}
