package com.toan.ecommercedemo.model.search;

public class CommentSearch extends BaseDateSearch {

    private static final long serialVersionUID = 1L;

    private Long customerId;

    private Long productId;

    private Integer rating;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
