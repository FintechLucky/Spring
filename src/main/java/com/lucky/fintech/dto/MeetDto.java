package com.lucky.fintech.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter @Getter
@NoArgsConstructor
public class MeetDto {

    private Long id;
    private String name;

    public MeetDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
