package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.CommentImageDao;
import com.toan.ecommercedemo.entities.CommentImage;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class CommentImageDaoImpl extends BaseDaoImpl<CommentImage, Long> implements CommentImageDao {
}
