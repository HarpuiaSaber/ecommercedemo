package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.UpdateStatusDto;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.CommentProductDto;
import com.toan.ecommercedemo.model.dto.ProductDto;
import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.search.BaseSearch;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", maxAge = -1)
public class ProductApi {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @PostMapping("/getCustomerProductWithoutComment")
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')")
    @PostMapping("/getAllPaging")
    @ResponseBody
    public ResponseDto<ProductDto> getAllProductWithPaging(@RequestBody ProductSearch search) {
        List<ProductDto> dtos = productService.getAllPaging(search);
        return new ResponseDto<ProductDto>(dtos.size(), productService.totalRecord(search), dtos);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')")
    @PostMapping("/updateStatus")
    @ResponseBody
    public void updateProductStatus(@RequestBody UpdateStatusDto body) throws InternalServerException {
        Integer status = body.getStatus();
        for (Long productId : body.getIds()) {
            productService.updateStatus(productId, status);
        }
    }
}
