package com.lucky.fintech.service;

import com.lucky.fintech.entity.Meet;
import com.lucky.fintech.entity.MeetHasUser;
import com.lucky.fintech.entity.User;
import com.lucky.fintech.repository.MeetRepository;
import com.lucky.fintech.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MeetService {
    private final UserRepository userRepository;
    private final MeetRepository meetRepository;

    /**
     * 모임 생성
     * param : 멤버 사용자들 아이디, 모임 이름
     */
    public void newMeet(@NotNull List<String> userList, String name) {
        // 모임 생성
        Meet meet = new Meet(name);

        // 사용자 추가
        for (String user_login_id : userList) {
            MeetHasUser meetHasUser = new MeetHasUser();

            User user = userRepository.findByUser_login_id(user_login_id).get();

            meetHasUser.setUser(user);
            meetHasUser.setMeet(meet);
            user.addMeetHasUser(meetHasUser);

        }

        meetRepository.save(meet);
    }
}
