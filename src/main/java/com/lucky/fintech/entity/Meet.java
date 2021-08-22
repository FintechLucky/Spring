package com.lucky.fintech.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Meet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Builder
    public Meet(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "meet")
    @JsonManagedReference // 순환참조 방지
    private List<MeetHasUser> meetHasUserList = new ArrayList<>();


}
