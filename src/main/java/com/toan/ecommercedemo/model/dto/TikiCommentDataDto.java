package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class TikiCommentDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String title;
    private String content;
    private int rating;
    private long product_id;
    private int thank_count;
    private List<TikiCommentImageDto> images;
    private TikiCustomerDto created_by;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public int getThank_count() {
        return thank_count;
    }

    public void setThank_count(int thank_count) {
        this.thank_count = thank_count;
    }

    public List<TikiCommentImageDto> getImages() {
        return images;
    }

    public void setImages(List<TikiCommentImageDto> images) {
        this.images = images;
    }

    public TikiCustomerDto getCreated_by() {
        return created_by;
    }

    public void setCreated_by(TikiCustomerDto created_by) {
        this.created_by = created_by;
    }
}
