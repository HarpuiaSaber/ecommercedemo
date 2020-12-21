package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.ContactDao;
import com.toan.ecommercedemo.entities.Contact;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.SaveContactDto;
import com.toan.ecommercedemo.model.dto.ViewContactDto;
import com.toan.ecommercedemo.services.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContactDao contactDao;


    @Override
    public void add(SaveContactDto dto) throws InternalServerException {
        //check userId ???
        Contact contact = modelMapper.map(dto, Contact.class);
        contact.setId(null);
        contactDao.add(contact);
        dto.setId(contact.getId());
    }

    @Override
    public void update(SaveContactDto dto) throws InternalServerException {
        Contact old = contactDao.getById(dto.getId());
        if (old != null) {
            old = modelMapper.map(dto, Contact.class);
            contactDao.update(old);
        } else {
            throw new InternalServerException("Địa chỉ không tồn tại");
        }
    }

    @Override
    public void delete(Long id) throws InternalServerException {
        Contact old = contactDao.getById(id);
        if (old != null) {
            old.setDeleted(true);
            contactDao.update(old);
        } else {
            throw new InternalServerException("Địa chỉ không tồn tại");
        }
    }

    @Override
    public ViewContactDto getById(Long id) throws InternalServerException {
        Contact entity = contactDao.getById(id);
        if (entity != null) {
            ViewContactDto dto = new ViewContactDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setAddress(entity.getAddress());
            dto.setPhone(entity.getPhone());
            dto.setLocation(String.format("%s, %s, %s",
                    entity.getVillage().getName(),
                    entity.getVillage().getDistrict().getName(),
                    entity.getVillage().getDistrict().getProvince().getName()));
            dto.setProvinceId(entity.getVillage().getDistrict().getProvince().getId());
            dto.setDistrictId(entity.getVillage().getDistrict().getId());
            dto.setVillageId(entity.getVillage().getId());
            return dto;
        } else {
            throw new InternalServerException("Địa chỉ không tồn tại");
        }
    }

    @Override
    public List<ViewContactDto> getContactOfUser(Long userId) {
        List<Contact> contacts = contactDao.getContactOfUser(userId);
        List<ViewContactDto> dtos = new ArrayList<ViewContactDto>();
        for (Contact entity : contacts) {
            ViewContactDto dto = new ViewContactDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setAddress(entity.getAddress());
            dto.setPhone(entity.getPhone());
            dto.setLocation(String.format("%s, %s, %s",
                    entity.getVillage().getName(),
                    entity.getVillage().getDistrict().getName(),
                    entity.getVillage().getDistrict().getProvince().getName()));
            dto.setProvinceId(entity.getVillage().getDistrict().getProvince().getId());
            dto.setDistrictId(entity.getVillage().getDistrict().getId());
            dto.setVillageId(entity.getVillage().getId());
            dtos.add(dto);
        }
        return dtos;
    }
}
