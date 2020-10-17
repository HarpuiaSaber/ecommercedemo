package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.CommentDto;
import com.toan.ecommercedemo.model.dto.ViewCommentDto;
import com.toan.ecommercedemo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "", maxAge = -1)
public class CommentApi {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @ResponseBody
    public CommentDto addComment(@RequestBody CommentDto dto){
        return dto;
    }

    @GetMapping
    @ResponseBody
    public ResponseDto<ViewCommentDto> getAllPaging(@RequestBody CommentDto dto){
        return null;
    }
}

