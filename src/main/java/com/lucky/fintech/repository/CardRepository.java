package com.lucky.fintech.repository;

import com.lucky.fintech.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
