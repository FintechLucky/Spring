package com.lucky.fintech.controller;

import com.lucky.fintech.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MeetController {
    private final MeetService meetService;

    @PostMapping("/meet/save")
    @ResponseBody
    public void newMeet(@RequestBody Map<String, List<String>> meetInfo) {
        List<String> userList = meetInfo.get("userList");
        String meetName = meetInfo.get("meetName").get(0);
        meetService.newMeet(userList, meetName);
    }
}
