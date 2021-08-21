package com.lucky.fintech.repository;

import com.lucky.fintech.dto.MeetDto;
import com.lucky.fintech.entity.Meet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetRepository extends JpaRepository<Meet, Long> {
    @Query("select new com.lucky.fintech.dto.MeetDto(m.id,m.name) from Meet m where m.id = any(select mhu.meet.id from MeetHasUser mhu where mhu.user.id = (select u.id from User u where u.user_login_id = :user_login_id))")
    List<MeetDto> findMeetsByUser_login_id(@Param("user_login_id") String user_login_id);
}

