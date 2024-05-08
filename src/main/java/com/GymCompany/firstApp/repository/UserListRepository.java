package com.GymCompany.firstApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GymCompany.firstApp.model.UserListDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserListRepository extends JpaRepository<UserListDTO, Integer> {
	
	
    //Optional<UserListDTO> findByUserId(String userId);
    
    @Modifying
    @Query(value = "INSERT INTO USERLIST (ULID, USERID, USERPW, USERNAME, JOIN_DATE, LASTLOGINTIME) VALUES (userList_seq.nextval, ?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void insertUser(String userId, String userPw, String userName, LocalDate joinDate, LocalDateTime lastLoginTime);

}
