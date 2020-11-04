package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.CommentImageDao;
import com.toan.ecommercedemo.entities.Comment;
import com.toan.ecommercedemo.entities.CommentImage;
import com.toan.ecommercedemo.model.dto.CommentDto;
import com.toan.ecommercedemo.model.dto.TikiCommentDataDto;
import com.toan.ecommercedemo.model.dto.TikiCommentImageDto;
import com.toan.ecommercedemo.services.CommentImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentImageServiceImpl implements CommentImageService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentImageDao commentImageDao;

    @Override
    public void addFromTiki(TikiCommentDataDto dto) {
        List<TikiCommentImageDto> imageDtos = dto.getImages();
        for (TikiCommentImageDto imageDto : imageDtos) {
            CommentImage commentImage = new CommentImage();
            commentImage.setPath(imageDto.getFull_path());
            commentImage.setFromTiki(true);
            commentImage.setComment(new Comment(dto.getId()));
            commentImageDao.add(commentImage);
        }
    }

    @Override
    public void add(CommentDto dto) {
        if (dto.getImage1() != null) {
            CommentImage commentImage = new CommentImage();
            commentImage.setPath(dto.getImage1());
            commentImage.setComment(new Comment(dto.getId()));
            commentImage.setFromTiki(false);
            commentImageDao.add(commentImage);
        }
        if (dto.getImage2() != null) {
            CommentImage commentImage = new CommentImage();
            commentImage.setPath(dto.getImage2());
            commentImage.setComment(new Comment(dto.getId()));
            commentImage.setFromTiki(false);
            commentImageDao.add(commentImage);
        }
        if (dto.getImage3() != null) {
            CommentImage commentImage = new CommentImage();
            commentImage.setPath(dto.getImage3());
            commentImage.setComment(new Comment(dto.getId()));
            commentImage.setFromTiki(false);
            commentImageDao.add(commentImage);
        }
        if (dto.getImage4() != null) {
            CommentImage commentImage = new CommentImage();
            commentImage.setPath(dto.getImage4());
            commentImage.setComment(new Comment(dto.getId()));
            commentImage.setFromTiki(false);
            commentImageDao.add(commentImage);
        }
        if (dto.getImage5() != null) {
            CommentImage commentImage = new CommentImage();
            commentImage.setPath(dto.getImage5());
            commentImage.setComment(new Comment(dto.getId()));
            commentImage.setFromTiki(false);
            commentImageDao.add(commentImage);
        }
    }
}
