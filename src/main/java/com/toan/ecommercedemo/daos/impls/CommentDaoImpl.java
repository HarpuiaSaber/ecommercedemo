package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.CommentDao;
import com.toan.ecommercedemo.entities.Comment;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class CommentDaoImpl extends BaseDaoImpl<Comment, Long> implements CommentDao {
}
