package com.toan.ecommercedemo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class CommentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String content;

    private Integer rating;

    private Long productId;

    private Long customerId;

    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;

    @JsonIgnore
    private MultipartFile imageFile1;
    @JsonIgnore
    private MultipartFile imageFile2;
    @JsonIgnore
    private MultipartFile imageFile3;
    @JsonIgnore
    private MultipartFile imageFile4;
    @JsonIgnore
    private MultipartFile imageFile5;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public MultipartFile getImageFile1() {
        return imageFile1;
    }

    public void setImageFile1(MultipartFile imageFile1) {
        this.imageFile1 = imageFile1;
    }

    public MultipartFile getImageFile2() {
        return imageFile2;
    }

    public void setImageFile2(MultipartFile imageFile2) {
        this.imageFile2 = imageFile2;
    }

    public MultipartFile getImageFile3() {
        return imageFile3;
    }

    public void setImageFile3(MultipartFile imageFile3) {
        this.imageFile3 = imageFile3;
    }

    public MultipartFile getImageFile4() {
        return imageFile4;
    }

    public void setImageFile4(MultipartFile imageFile4) {
        this.imageFile4 = imageFile4;
    }

    public MultipartFile getImageFile5() {
        return imageFile5;
    }

    public void setImageFile5(MultipartFile imageFile5) {
        this.imageFile5 = imageFile5;
    }
}
