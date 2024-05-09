package com.GymCompany.firstApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GymCompany.firstApp.model.UserListDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface UserListRepository extends JpaRepository<UserListDTO, Integer> {
	
	
    //Optional<UserListDTO> findByUserId(String userId);
    
    @Modifying
    @Query(value = "INSERT INTO USERLIST (ULID, USERID, USERPW, USERNAME, JOIN_DATE, LAST_LOGIN_TIME) VALUES (userList_seq.nextval, ?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void insertUser(String userId, String userPw, String userName, LocalDate joinDate, LocalDateTime lastLoginTime);
    
    @Modifying
    @Query(value= "SELECT Count(*) FROM USERLIST WHERE USERID= ?1 ",nativeQuery = true)   // 결과가 뭐가되든 0만나옴 데이터 바인딩이잘안됫나?
    int userRddCheck(String userId);
    
   
    
    
    
}
