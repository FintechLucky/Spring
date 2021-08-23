package com.lucky.fintech.controller;

import com.lucky.fintech.dto.UserDto;
import com.lucky.fintech.entity.Card;
import com.lucky.fintech.entity.User;
import com.lucky.fintech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원 가입
     * @param map - 회원가입 정보
     * @return User 객체
     */
    @PostMapping("/user/new")
    @ResponseBody
    public User join(@RequestBody Map<String, String> map) {
        User user = new User(map.get("user_id"), map.get("user_pass"), map.get("user_name"));

        Card card = new Card();
        card.setCard_company(map.get("card_company"));
        card.setCard_number(map.get("card_number"));
        card.setCard_validity(map.get("card_validity"));
        card.setCard_cvc(Integer.parseInt(map.get("card_cvc")));

        return userService.join(user, card).get();
    }


    /**
     * 로그인
     * @param map - 로그인 정보
     * @return 로그인 검사 결과 전달
     */
    @PostMapping("/user/login")
    @ResponseBody
    public Optional<UserDto> login(@RequestBody Map<String, String> map) {
        Optional<UserDto> result = Optional.of(new UserDto());
        Optional<User> foundUser = userService.findByUser_login_id(map.get("user_id"));
        if (foundUser.isPresent()) {
            if (foundUser.get().getUser_login_pass().equals(map.get("user_pass"))) {
                result.get().setUser_name(foundUser.get().getUser_name());
                result.get().setUser_id(foundUser.get().getUser_login_id());
                return result;
            } else {
                result.get().setUser_id("wrong_pass");
                return result;
            }
        } else {
            result.get().setUser_id("no_user");
            return result;
        }

    }

    /**
     * 모든 사용자 찾기
     * @return 모든 사용자 Dto 객체 리스트 전달
     */
    @GetMapping("/user/list")
    @ResponseBody
    public List<UserDto> findAllUser() {
        return userService.findAllUser();
    }
}
