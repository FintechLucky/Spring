package com.lucky.fintech.service;

import com.lucky.fintech.dto.UserDto;
import com.lucky.fintech.entity.Card;
import com.lucky.fintech.entity.User;
import com.lucky.fintech.repository.CardRepository;
import com.lucky.fintech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    /**
     * 회원 가입
     * @param user, card - 사용자 정보 및 기본 카드 정보 등록
     * @return 사용자 객체 반환
     */
    public Optional<User> join(User user, Card card) {
        user.addCard(card);
        userRepository.save(user); // 회원 객체 DB에 등록
        cardRepository.save(card); // 카드 객체 DB에 등록

        return userRepository.findById(user.getId());
    }

    /**
     * 사용자 아이디로 사용자 객체 찾기
     * @param user_login_id
     * @return 사용자 객체 반환
     */
    public Optional<User> findByUser_login_id(String user_login_id) {
        return userRepository.findByUser_login_id(user_login_id);
    }


    public List<UserDto> findAllUser() {
        return userRepository.findAllUserList();
    }

}
