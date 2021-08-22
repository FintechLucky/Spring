package com.lucky.fintech.dto;

import lombok.*;

@Data
@Setter @Getter
@NoArgsConstructor
public class PayMeetDto {

    private String name;
    private int totalPrice;
    private boolean status;

    @Builder
    public PayMeetDto(String name, int totalPrice, boolean status) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
