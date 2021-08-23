package com.lucky.fintech.controller;

import com.lucky.fintech.dto.MeetDto;
import com.lucky.fintech.entity.Meet;
import com.lucky.fintech.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MeetController {
    private final MeetService meetService;

    /**
     * 모임 생성
     * @param meetInfo - 모임 세부 정보
     * @return 생성된모임 객체 Dto 전달
     */
    @PostMapping("/meet/save")
    @ResponseBody
    public MeetDto newMeet(@RequestBody Map<String, List<String>> meetInfo) {
        List<String> userList = meetInfo.get("userList");
        String meetName = meetInfo.get("meetName").get(0);
        if (meetName.equals("")) {
            meetName = userList.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(", "));
        }
        Meet meet = meetService.newMeet(userList, meetName);
        MeetDto result = new MeetDto(meet.getId(), meet.getName());
        return result;
    }


    /**
     * 사용자 아이디로 사용자가 속해있는 모임 리스트 조회
     * @param user_login_id - 사용자 로그인 아이디
     * @return 해당 사용자 아이디가 속해있는 모임 객체 Dto 리스트 전달
     */
    @GetMapping("/meet/list")
    @ResponseBody
    public List<MeetDto> listMeet(@RequestParam String user_login_id) {
        return meetService.findMeetsByUser_login_id(user_login_id);
    }
}
