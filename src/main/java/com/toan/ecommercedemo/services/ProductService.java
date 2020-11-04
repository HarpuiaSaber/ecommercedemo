package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.ProductSearch;

import java.util.List;

public interface ProductService {
    //    public void add() throws InternalServerException;
//
//    public void update() throws InternalServerException;
//
    public void delete(Long id) throws InternalServerException;

    public ViewProductDto getById(Long id);

    public CartProductDto getCartProductById(Long id);

    public List<ShortProductDto> searchWithPaging(ProductSearch search);

    public Long totalRecord(ProductSearch search);

    public void addFromTiki(TikiProductDto dto);

    public List<Long> getAllId();

    public List<CommentProductDto> getCustomerProductWithoutComment(Long customerId, Integer start, Integer length);

    public Long countCustomerProductWithoutComment(Long customerId);

    public List<ShortProductDto> getCustomerProductInComment(Long customerId);
}
