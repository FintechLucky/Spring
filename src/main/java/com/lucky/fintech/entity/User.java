package com.lucky.fintech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"user_login_id"}
        )})
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_login_id")
    private String user_login_id;
    private String user_login_pass;
    private String user_name;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Card> cardList = new ArrayList<>();

    // 사용자에게 카드 추가
    public void addCard(Card card) {
        this.cardList.add(card);
        if (card.getUser() != this) {
            card.setUser(this);
        }
    }
}
