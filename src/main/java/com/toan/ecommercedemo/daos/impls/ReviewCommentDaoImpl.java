package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ReviewCommentDao;
import com.toan.ecommercedemo.entities.ReviewComment;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ReviewCommentDaoImpl extends BaseDaoImpl<ReviewComment, Long> implements ReviewCommentDao {
}
