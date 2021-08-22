package com.lucky.fintech.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "pay")
public class Pay implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "pay_meet_id")
    private PayMeet payMeet;

    @Id
    @ManyToOne
    @JoinColumn(name = "meet_id")
    private Meet meet;

    @Column(name = "price")
    private int price;

    @Column(name = "pay_status")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean payStatus;
}
