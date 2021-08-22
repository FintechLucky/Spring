package com.lucky.fintech.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class PayMeet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meet_id")
    private Meet meet;

    @Column(name = "name")
    private String name;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "start")
    private String start;

    @Column(name = "end")
    private String end;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "status")
    private boolean status;


    @Builder
    public PayMeet( String name, String start, int totalPrice) {
        this.name = name;
        this.start = start;
        this.totalPrice = totalPrice;
        this.status = false;
    }

    @OneToMany(mappedBy = "payMeet")
    @JsonManagedReference // 순환참조 방지
    private List<Pay> payList = new ArrayList<>();

}
