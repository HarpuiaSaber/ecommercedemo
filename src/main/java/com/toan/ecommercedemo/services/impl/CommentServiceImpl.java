package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.CommentDao;
import com.toan.ecommercedemo.entities.Comment;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.enums.CommentStatus;
import com.toan.ecommercedemo.model.dto.TikiCommentDataDto;
import com.toan.ecommercedemo.services.CommentService;
import com.toan.ecommercedemo.utils.RandomUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentDao commentDao;

    @Override
    public void addFromTiki(TikiCommentDataDto dto) {
        Comment comment = new Comment();
        comment.setTitle(dto.getTitle());
        comment.setContent(dto.getContent());
        comment.setRating(dto.getRating());
        comment.setProduct(new Product(dto.getProduct_id()));
        comment.setCustomer(new User(dto.getCreated_by().getId()));
        comment.setStatus(CommentStatus.APPROVED);
        comment.setHelpfulNumber(dto.getThank_count());
        if (dto.getThank_count() > 0) {
            comment.setNotHelpfulNumber(RandomUtils.getRandomIntInRange(0, (int) (dto.getThank_count() * 1.5)));
        } else {
            comment.setNotHelpfulNumber(0);
        }
        commentDao.add(comment);
        dto.setId(comment.getId());
    }
}
