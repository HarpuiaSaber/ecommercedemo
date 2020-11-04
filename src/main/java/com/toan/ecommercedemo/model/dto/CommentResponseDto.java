package com.toan.ecommercedemo.model.dto;

import java.util.List;

public class CommentResponseDto extends ResponseDto<ViewCommentDto> {

    private double rating;
    private List<Long> starComment;
    private long totalComment;

    public CommentResponseDto(long recordsTotal, long recordsFiltered, List<ViewCommentDto> data) {
        super(recordsTotal, recordsFiltered, data);
    }

    public CommentResponseDto(long recordsTotal, List<ViewCommentDto> data) {
        super(recordsTotal, data);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Long> getStarComment() {
        return starComment;
    }

    public void setStarComment(List<Long> starComment) {
        this.starComment = starComment;
    }

    public long getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(long totalComment) {
        this.totalComment = totalComment;
    }
}
