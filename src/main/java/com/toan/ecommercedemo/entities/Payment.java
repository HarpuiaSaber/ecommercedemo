package com.toan.ecommercedemo.entities;

import com.toan.ecommercedemo.enums.PaymentMethod;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment")
public class Payment extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "method")
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod method;

    public Payment() {
    }

    public Payment(Long id) {
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

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
}
