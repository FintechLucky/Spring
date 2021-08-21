package com.lucky.fintech.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;
    private String card_company;
    private String card_number;
    private String card_validity;
    private int card_cvc;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
