package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.SaveContactDto;
import com.toan.ecommercedemo.model.dto.ViewContactDto;
import com.toan.ecommercedemo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*", maxAge = -1)
public class ContactApi {

    @Autowired
    private ContactService contactService;

    @GetMapping("/getContact")
    @ResponseBody
    public ResponseDto<ViewContactDto> getContactOfCustomer() throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                List<ViewContactDto> dtos = contactService.getContactOfUser(principal.getId());
                return new ResponseDto<ViewContactDto>(dtos.size(), dtos.size(), dtos);
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }

    @PostMapping("/addContact")
    @ResponseBody
    public SaveContactDto addContactOfCustomer(@RequestBody SaveContactDto dto) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                dto.setCustomerId(principal.getId());
                contactService.add(dto);
                return dto;
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }

    @PostMapping("/editContact")
    @ResponseBody
    public SaveContactDto editContactOfCustomer(@RequestBody SaveContactDto dto) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                dto.setCustomerId(principal.getId());
                contactService.update(dto);
                return dto;
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }

    @DeleteMapping("/deleteContact")
    public @ResponseBody
    void deleteContactOfCustomer(@RequestParam long id) throws InternalServerException {
        contactService.delete(id);
    }
}