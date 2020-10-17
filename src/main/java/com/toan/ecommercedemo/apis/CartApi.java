package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.CartProductDto;
import com.toan.ecommercedemo.model.dto.ItemDto;
import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*", maxAge = -1)
public class CartApi {

    @Autowired
    private ProductService productService;

    @GetMapping("/cart/list")
    @ResponseBody
    public ResponseDto<ItemDto> getCartOfCustomer(HttpSession httpSession) {
        Object object = httpSession.getAttribute("cart");
        if (object != null) {
            List<ItemDto> dtos = new ArrayList<ItemDto>(((Map<Long, ItemDto>) object).values());
            return new ResponseDto<ItemDto>(dtos.size(), dtos.size(), dtos);
        }
        return new ResponseDto<ItemDto>(0, 0, new ArrayList<ItemDto>());
    }

    @GetMapping("/cart/add")
    @ResponseBody
    public void addToCart(HttpSession httpSession,
                          @RequestParam Long productId, @RequestParam Integer quantity) throws InternalServerException {
        if (quantity == null) {
            quantity = 1;
        }
        if (productId != null) {
            CartProductDto product = productService.getCartProductById(productId);
            Object object = httpSession.getAttribute("cart");
            if (object == null) {
                ItemDto item = new ItemDto();
                item.setProduct(product);
                item.setQuantity(quantity);
                item.setUnitPrice(product.getUnitPrice());

                Map<Long, ItemDto> map = new HashMap<>();
                map.put(item.getProduct().getId(), item);
                httpSession.setAttribute("cart", map);
            } else {
                Map<Long, ItemDto> map = (Map<Long, ItemDto>) object;
                ItemDto item = map.get(product.getId());
                if (item == null) {
                    item = new ItemDto();
                    item.setProduct(product);
                    item.setQuantity(quantity);
                    item.setUnitPrice(product.getUnitPrice());
                    map.put(product.getId(), item);
                    httpSession.setAttribute("cart", map);
                } else {
                    item.setQuantity(quantity + item.getQuantity());
                    httpSession.setAttribute("cart", map);
                }
            }
        } else {
            throw new InternalServerException("Không có sản phẩm");
        }
    }

    @GetMapping("/cart/edit")
    @ResponseBody
    public void editCart(HttpSession httpSession,
                         @RequestParam Long productId, @RequestParam Integer quantity) throws InternalServerException {
        if (quantity == null) {
            quantity = 1;
        }
        if (productId != null) {
            Object object = httpSession.getAttribute("cart");
            if (object == null) {
                throw new InternalServerException("Không có giỏ hàng");
            } else {
                CartProductDto product = productService.getCartProductById(productId);
                Map<Long, ItemDto> map = (Map<Long, ItemDto>) object;
                ItemDto item = map.get(product.getId());
                item.setQuantity(quantity);
                httpSession.setAttribute("cart", map);
            }
        } else {
            throw new InternalServerException("Không có sản phẩm");
        }
    }

    @DeleteMapping("/cart/delete")
    @ResponseBody
    public void deleteCart(HttpSession httpSession, @RequestParam long id) {
        Object object = httpSession.getAttribute("cart");
        if (object != null) {
            Map<Long, ItemDto> map = (Map<Long, ItemDto>) object;
            map.remove(id);
            httpSession.setAttribute("cart", map);
        }
    }
}
