package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.enums.Role;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.search.BaseSearch;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import com.toan.ecommercedemo.services.ShopService;
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

    @Autowired
    private ShopService shopService;

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
    public ResponseDto<ProductDto> getAllProductWithPaging(@RequestBody ProductSearch search) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                if (principal.getRole() == Role.SELLER) {
                    search.setShopId(shopService.getShopIdOfSeller(principal.getId()));
                }
                List<ProductDto> dtos = productService.getAllPaging(search);
                return new ResponseDto<ProductDto>(dtos.size(), productService.totalRecord(search), dtos);
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");

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
