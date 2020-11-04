package com.toan.ecommercedemo.apis;

import com.google.common.base.Strings;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.CommentSearch;
import com.toan.ecommercedemo.services.CommentImageService;
import com.toan.ecommercedemo.services.CommentService;

import com.toan.ecommercedemo.utils.Constants;
import com.toan.ecommercedemo.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "", maxAge = -1)
public class CommentApi {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentImageService commentImageService;

    @PostMapping("/customer/comment/add")
    @ResponseBody
    public CommentDto addComment(@ModelAttribute CommentDto dto) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                dto.setCustomerId(principal.getId());
                if (Strings.isNullOrEmpty(dto.getTitle())) {
                    switch (dto.getRating()) {
                        case 1:
                            dto.setTitle("Rất không hài lòng");
                            break;
                        case 2:
                            dto.setTitle("Không hài lòng");
                            break;
                        case 3:
                            dto.setTitle("Bình thường");
                            break;
                        case 4:
                            dto.setTitle("Hài lòng");
                            break;
                        case 5:
                            dto.setTitle("Cưc kỳ hài lòng");
                            break;
                    }
                }
                if (dto.getImageFile1() != null && !dto.getImageFile1().isEmpty()) {
                    dto.setImage1(FileUtils.saveFileWithSpecialNameByTime(dto.getImageFile1(), Constants.folderComment));
                }
                if (dto.getImageFile2() != null && !dto.getImageFile2().isEmpty()) {
                    dto.setImage2(FileUtils.saveFileWithSpecialNameByTime(dto.getImageFile2(), Constants.folderComment));
                }
                if (dto.getImageFile3() != null && !dto.getImageFile3().isEmpty()) {
                    dto.setImage3(FileUtils.saveFileWithSpecialNameByTime(dto.getImageFile3(), Constants.folderComment));
                }
                if (dto.getImageFile4() != null && !dto.getImageFile4().isEmpty()) {
                    dto.setImage4(FileUtils.saveFileWithSpecialNameByTime(dto.getImageFile4(), Constants.folderComment));
                }
                if (dto.getImageFile5() != null && !dto.getImageFile5().isEmpty()) {
                    dto.setImage5(FileUtils.saveFileWithSpecialNameByTime(dto.getImageFile5(), Constants.folderComment));
                }
                commentService.add(dto);
                commentImageService.add(dto);
                return dto;
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }

    @PostMapping("/comment/getAllPaging")
    @ResponseBody
    public CommentResponseDto getAllCommentPaging(@RequestBody CommentSearch search) {
        List<ViewCommentDto> dtos = commentService.searchWithPaging(search);
        CommentResponseDto responseDto = new CommentResponseDto(dtos.size(), commentService.getTotalRecord(search), dtos);
        responseDto.setTotalComment(commentService.getTotalRecord(search));
        responseDto.setRating(commentService.getRatting(search));
        List<Long> stars = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            search.setRating(i);
            stars.add(commentService.getTotalRecord(search));
        }
        responseDto.setStarComment(stars);
        return responseDto;
    }

    @PostMapping("/customer/comment/getAllPaging")
    @ResponseBody
    public ResponseDto<CustomerCommentDto> getCommentOfCustomer(@RequestBody CommentSearch search) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                search.setCustomerId(principal.getId());
                List<CustomerCommentDto> dtos = commentService.getCustomerCommentPaging(search);
                return new ResponseDto<CustomerCommentDto>(dtos.size(), commentService.getTotalRecord(search), dtos);
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }
}

