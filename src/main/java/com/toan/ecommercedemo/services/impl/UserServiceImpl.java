package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.UserDao;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.enums.Gender;
import com.toan.ecommercedemo.enums.Role;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.UserSearch;
import com.toan.ecommercedemo.services.UserService;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import com.toan.ecommercedemo.utils.PasswordGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        UserPrincipal principal = new UserPrincipal(user.getUsername(), user.getPassword(), user.getEnabled(), true,
                true, true, authorities);
        principal.setId(user.getId());
        principal.setName(user.getName());
        principal.setRole(user.getRole());

        return principal;
    }

    @Override
    public void add(AddUserDto dto) throws InternalServerException {
        User old = userDao.getByUsername(dto.getUsername());
        if (old == null) {
            User user = modelMapper.map(dto, User.class);
            user.setId(null);
            user.setRole(Role.valueOf(dto.getRole()));
            user.setGender(Gender.valueOf(dto.getGender()));
            user.setEnabled(true);
            userDao.add(user);
            dto.setId(user.getId());
        } else {
            throw new InternalServerException("Username đã tồn tại");
        }
    }

    @Override
    public void update(UpdateUserDto dto) throws InternalServerException {
        User old = userDao.getById(dto.getId());
        String username = old.getUsername();
        String password = old.getPassword();
        if (old != null) {
            old = modelMapper.map(dto, User.class);
            old.setUsername(username);
            old.setPassword(password);
            old.setGender(Gender.valueOf(dto.getGender()));
            userDao.update(old);
        } else {
            throw new InternalServerException("User không tồn tại");
        }
    }

    @Override
    public void delete(Long id) throws InternalServerException {
        User old = userDao.getById(id);
        if (old != null) {
            userDao.delete(old);
        } else {
            throw new InternalServerException("User không tồn tại");
        }
    }

    @Override
    public ViewUserDto getById(Long id) throws InternalServerException {
        User entity = userDao.getById(id);
        if (entity != null) {
            ViewUserDto dto = new ViewUserDto();
            dto.setId(entity.getId());
            dto.setDob(DateTimeUtils.formatDate(entity.getDob(), DateTimeUtils.DD_MM_YYYY));
            dto.setEmail(entity.getEmail());
            dto.setGender(entity.getGender().getValue());
            dto.setName(entity.getName());
            dto.setPhone(entity.getPhone());
            dto.setRole(entity.getRole());
            dto.setUsername(entity.getUsername());
            return dto;
        } else {
            throw new InternalServerException("User không tồn tại");
        }
    }

    @Override
    public List<ViewUserDto> search(UserSearch search) {
        return null;
    }

    @Override
    public void addSellerFromTiki(TikiSellerDto dto) {
        User oldSeller = userDao.getByName(dto.getName());
        if (oldSeller == null) {
            User seller = new User();
            seller.setName(dto.getName().trim());
            seller.setUsername(dto.getName().trim().replaceAll("\\s", "").toLowerCase());
            seller.setPassword(PasswordGenerator.getHashString("123qwe"));
            seller.setRole(Role.SELLER);
            seller.setEnabled(true);
            seller.setPhone("0123456789");
            seller.setEmail(dto.getName().trim().replaceAll("\\s", "").toLowerCase() + "@gmail.com");
            userDao.add(seller);
            dto.setId(seller.getId());
        } else {
            dto.setId(oldSeller.getId());
        }
    }

    @Override
    public void addCustommerFromTiki(TikiCustomerDto dto) {
        User oldCustomer = userDao.getById(dto.getId());
        if (oldCustomer == null) {
            User customer = new User();
            customer.setId(dto.getId());
            customer.setName(dto.getName());
            customer.setRole(Role.CUSTOMER);
            customer.setEnabled(true);
            userDao.add(customer);
            dto.setId(customer.getId());
        } else {
            dto.setId(oldCustomer.getId());
        }
    }
}
