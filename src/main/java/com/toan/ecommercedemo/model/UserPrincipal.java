package com.toan.ecommercedemo.model;

import java.io.Serializable;
import java.util.Collection;

import com.toan.ecommercedemo.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserPrincipal extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Role role;

    public UserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
                         boolean credentialsNonExpired, boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}