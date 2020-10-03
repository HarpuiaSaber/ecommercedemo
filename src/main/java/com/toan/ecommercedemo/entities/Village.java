package com.toan.ecommercedemo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "village")
public class Village implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

}
