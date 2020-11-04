package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.enums.ProductStatus;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.CommentProductDto;
import com.toan.ecommercedemo.model.dto.CustomerCommentDto;
import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.ShortProductDto;
import com.toan.ecommercedemo.model.search.BaseSearch;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class ProductApi {

    @Autowired
    private ProductService productService;

    @PostMapping("/searchProductPaging")
    @ResponseBody
    public ResponseDto<ShortProductDto> searchProductWithPaging(@RequestBody ProductSearch search) {
        search.setStatus(ProductStatus.ACCEPTED.getValue());
        List<ShortProductDto> dtos = productService.searchWithPaging(search);
        return new ResponseDto<ShortProductDto>(dtos.size(), productService.totalRecord(search), dtos);
    }

    @PostMapping("/customer/getProductWithoutComment")
    @ResponseBody
    public ResponseDto<CommentProductDto> getCustomerProductWithoutComment(@RequestBody BaseSearch search) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                List<CommentProductDto> dtos = productService.getCustomerProductWithoutComment(principal.getId(), search.getStart(), search.getLength());
                return new ResponseDto<CommentProductDto>(dtos.size(), productService.countCustomerProductWithoutComment(principal.getId()), dtos);
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }
}
