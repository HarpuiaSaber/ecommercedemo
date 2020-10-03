package com.toan.ecommercedemo.entities;

import com.toan.ecommercedemo.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order_history")
public class OrderHistory extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;
}
