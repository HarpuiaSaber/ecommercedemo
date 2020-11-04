package com.toan.ecommercedemo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shop")
public class Shop extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "logo")
    private String logo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(name = "from_tiki")
    private Boolean fromTiki;

    @Column(name = "description", length = 500)
    private String description;

    public Shop() {
    }

    public Shop(Long id) {
        this.id = id;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Boolean getFromTiki() {
        return fromTiki;
    }

    public void setFromTiki(Boolean fromTiki) {
        this.fromTiki = fromTiki;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
