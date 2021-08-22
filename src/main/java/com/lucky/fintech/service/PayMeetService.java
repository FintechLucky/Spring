package com.lucky.fintech.service;

import com.lucky.fintech.entity.Meet;
import com.lucky.fintech.entity.Pay;
import com.lucky.fintech.entity.PayMeet;
import com.lucky.fintech.entity.User;
import com.lucky.fintech.repository.MeetRepository;
import com.lucky.fintech.repository.PayMeetRepository;
import com.lucky.fintech.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PayMeetService {

    private final UserRepository userRepository;
    private final PayMeetRepository payMeetRepository;
    private final MeetRepository meetRepository;

    /**
     * 새 정산 생성
     */
    public PayMeet newPayMeet(@NotNull Map<String, List<String>> userPayList, String name, Long meet_id, int total_price) {
        // 현재 날짜 불러오기
        String Today = getToday();

        // 새 정산 생성
        PayMeet payMeet = new PayMeet( name, Today, total_price);

        // 모임 불러오기
        Meet meet = meetRepository.findById(meet_id).get();

        for (String key : userPayList.keySet()) {
            Pay pay = new Pay();
            User user = userRepository.findByUser_login_id(userPayList.get(key).get(0)).get();

            pay.setPayMeet(payMeet);
            pay.setMeet(meet);
            pay.setUser(user);
            pay.setPrice(Integer.parseInt(userPayList.get(key).get(1)));
            pay.setPayStatus(false);

            user.addPay(pay);

        }

        PayMeet result =  payMeetRepository.save(payMeet);
        System.out.println(result);
        return result;
    }

    /**
     * 사용자 아이디로 현재 승인이 안된 정산 목록 불러오기
     */

    /**
     * 사용자 아이디로 승인은 완료하였지만, 끝이 안난 정산 목록 불러오기
     */

    /**
     * 사용자 아이디로 끝난 정산 목록 불러오기
     */

    /**
     * 현재 날짜 불러오기
     */
    public String getToday() {
        LocalDate now = LocalDate.now();

        //포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return now.format(formatter);
    }
}
