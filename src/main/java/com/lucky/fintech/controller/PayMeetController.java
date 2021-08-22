package com.lucky.fintech.controller;

import com.lucky.fintech.dto.PayMeetDto;
import com.lucky.fintech.entity.PayMeet;
import com.lucky.fintech.service.PayMeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PayMeetController {
    private final PayMeetService payMeetService;

    /**
     * param :
     */
    @PostMapping("/paymeet/save")
    @ResponseBody
    public PayMeetDto newPayMeet(@RequestBody Map<String, Map<String, List<String>>> payMeetInfo) {
        Map<String, List<String>> userList = payMeetInfo.get("userList");
        Map<String, List<String>> payInfo = payMeetInfo.get("payMeetInfo"); // [String name, String meet_id, String total_price]

        String name = payInfo.get("name").get(0);
        Long meet_id = Long.valueOf(payInfo.get("meet").get(0));
        int total_price = Integer.parseInt(payInfo.get("total").get(0));

        PayMeet payMeet = payMeetService.newPayMeet(userList, name, meet_id, total_price);

        PayMeetDto result = new PayMeetDto(payMeet.getName(), payMeet.getTotalPrice(), payMeet.isStatus());

        return result;

    }
}
