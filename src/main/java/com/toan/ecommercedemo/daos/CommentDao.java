package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Comment;
import com.toan.ecommercedemo.model.search.CommentSearch;

import java.util.List;

public interface CommentDao extends BaseDao<Comment, Long> {

    public List<Comment> search(CommentSearch search);

    public List<Comment> searchWithPaging(CommentSearch search);

    public Long getTotalRecord(CommentSearch search);
}
