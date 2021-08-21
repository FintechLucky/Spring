package com.lucky.fintech.controller;

import com.lucky.fintech.entity.Card;
import com.lucky.fintech.entity.User;
import com.lucky.fintech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("user/new")
    @ResponseBody
    public User join(@RequestBody Map<String, String> map) {
        User user = new User();
        user.setUser_login_id(map.get("user_id"));
        user.setUser_login_pass(map.get("user_pass"));
        user.setUser_name(map.get("user_name"));

        Card card = new Card();
        card.setCard_company(map.get("card_company"));
        card.setCard_number(map.get("card_number"));
        card.setCard_validity(map.get("card_validity"));
        card.setCard_cvc(Integer.parseInt(map.get("card_cvc")));

        return userService.join(user, card).get();
    }
}
