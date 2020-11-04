package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.CommentDao;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.enums.CommentStatus;
import com.toan.ecommercedemo.model.dto.CommentDto;
import com.toan.ecommercedemo.model.dto.CustomerCommentDto;
import com.toan.ecommercedemo.model.dto.TikiCommentDataDto;
import com.toan.ecommercedemo.model.dto.ViewCommentDto;
import com.toan.ecommercedemo.model.search.CommentSearch;
import com.toan.ecommercedemo.services.CommentService;
import com.toan.ecommercedemo.utils.Constants;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import com.toan.ecommercedemo.utils.RandomUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Override
    public List<ViewCommentDto> searchWithPaging(CommentSearch search) {
        List<Comment> entities = commentDao.searchWithPaging(search);
        List<ViewCommentDto> dtos = new ArrayList<ViewCommentDto>();
        for (Comment entity : entities) {
            ViewCommentDto dto = new ViewCommentDto();
            dto.setId(entity.getId());
            dto.setCreatedDate(DateTimeUtils.formatDate(entity.getCreatedDate(), DateTimeUtils.DD_MM_YYYY));
            dto.setContent(entity.getContent());
            dto.setTitle(entity.getTitle());
            dto.setRating(entity.getRating());
            dto.setCustomerId(entity.getCustomer().getId());
            dto.setCustomerName(entity.getCustomer().getName());
            dto.setProductId(entity.getProduct().getId());
            List<CommentImage> imageEntities = entity.getImages();
            List<String> imageDtos = new ArrayList<>();
            for (CommentImage imageEntity : imageEntities) {
                if (imageEntity.getFromTiki()) {
                    imageDtos.add(imageEntity.getPath());
                } else {
                    imageDtos.add(Constants.baseUrl + Constants.folderImage + Constants.folderComment + imageEntity.getPath());
                }
            }
            dto.setImages(imageDtos);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Long getTotalRecord(CommentSearch search) {
        return commentDao.getTotalRecord(search);
    }

    @Override
    public Double getRatting(CommentSearch search) {
        List<Comment> entities = commentDao.search(search);
        if (entities.size() > 0) {
            double sumRating = 0;
            for (Comment comment : entities) {
                sumRating += comment.getRating();
            }
            return (double) Math.round(sumRating / entities.size() * 10) / 10;
        }
        return 0D;
    }

    @Override
    public List<CustomerCommentDto> getCustomerCommentPaging(CommentSearch search) {
        List<Comment> entities = commentDao.searchWithPaging(search);
        List<CustomerCommentDto> dtos = new ArrayList<CustomerCommentDto>();
        for (Comment entity : entities) {
            CustomerCommentDto dto = new CustomerCommentDto();
            dto.setId(entity.getId());
            dto.setCreatedDate(DateTimeUtils.formatDate(entity.getCreatedDate(), DateTimeUtils.DD_MM_YYYY));
            dto.setContent(entity.getContent());
            dto.setTitle(entity.getTitle());
            dto.setRating(entity.getRating());
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName());
            ProductImage productImage = entity.getProduct().getImages().get(0);
            if (productImage.getFromTiki()) {
                dto.setProductImage(productImage.getPath());
            } else {
                dto.setProductImage(Constants.baseUrl + productImage.getPath());
            }
            List<CommentImage> imageEntities = entity.getImages();
            List<String> imageDtos = new ArrayList<>();
            for (CommentImage imageEntity : imageEntities) {
                if (imageEntity.getFromTiki()) {
                    imageDtos.add(imageEntity.getPath());
                } else {
                    imageDtos.add(Constants.baseUrl + Constants.folderImage + Constants.folderComment + imageEntity.getPath());
                }
            }
            dto.setImages(imageDtos);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void add(CommentDto dto) {
        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setId(null);
        comment.setCustomer(new User(dto.getCustomerId()));
        comment.setProduct(new Product(dto.getProductId()));
        comment.setStatus(CommentStatus.WAITTINGFORACCEPT);
        commentDao.add(comment);
        dto.setId(comment.getId());
    }
}
