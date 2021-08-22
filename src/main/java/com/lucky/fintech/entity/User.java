package com.lucky.fintech.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference //순환참조 방지
    private List<MeetHasUser> meetHasUserList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Pay> payList = new ArrayList<>();

    @Builder
    public User(String user_login_id, String user_login_pass, String user_name) {
        this.user_login_id = user_login_id;
        this.user_login_pass = user_login_pass;
        this.user_name = user_name;
    }

    public User() {
    }

    // 사용자에게 카드 추가
    public void addCard(Card card) {
        this.cardList.add(card);
        if (card.getUser() != this) {
            card.setUser(this);
        }
    }


    // 사용자를 모임에 추가
    public void addMeetHasUser(MeetHasUser meetHasUser) {
        this.meetHasUserList.add(meetHasUser);
    }

    // 사용자를 pay 에 추가
    public void addPay(Pay pay) {
        this.payList.add(pay);
    }
}
