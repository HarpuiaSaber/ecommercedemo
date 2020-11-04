package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.CommentDto;
import com.toan.ecommercedemo.model.dto.TikiCommentDataDto;

public interface CommentImageService {

    public void addFromTiki(TikiCommentDataDto dto);

    public void add(CommentDto dto);
}
