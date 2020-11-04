package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.entities.Comment;
import com.toan.ecommercedemo.model.dto.CommentDto;
import com.toan.ecommercedemo.model.dto.CustomerCommentDto;
import com.toan.ecommercedemo.model.dto.TikiCommentDataDto;
import com.toan.ecommercedemo.model.dto.ViewCommentDto;
import com.toan.ecommercedemo.model.search.CommentSearch;

import java.util.List;

public interface CommentService {

    public void addFromTiki(TikiCommentDataDto dto);

    public List<ViewCommentDto> searchWithPaging(CommentSearch search);

    public Long getTotalRecord(CommentSearch search);

    public Double getRatting(CommentSearch search);

    public List<CustomerCommentDto> getCustomerCommentPaging(CommentSearch search);

    public void add(CommentDto dto);
}
