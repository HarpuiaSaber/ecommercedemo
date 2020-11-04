package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.ShortProductDto;

import java.util.List;

public interface RecommendService {

    public List<ShortProductDto> getRecommendation(Long userId);

    public List<Long> getRecommendationInMovieLens(Long userId, String path);

    public List<ShortProductDto> getRecommendationOfCustomer(Long userId, String path);

    public void createData();
}
