package com.lucky.fintech.service;

import com.lucky.fintech.entity.Card;
import com.lucky.fintech.entity.User;
import com.lucky.fintech.repository.CardRepository;
import com.lucky.fintech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        user.getCardList().add(card);
        userRepository.save(user); // 회원 객체 DB에 등록
        cardRepository.save(card); // 카드 객체 DB에 등록

        return userRepository.findById(user.getId());
    }


}
