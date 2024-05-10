package com.GymCompany.firstApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GymCompany.firstApp.model.UserListDTO;

@Repository
public interface UserListRepository extends JpaRepository<UserListDTO, Integer> {
	
	
	

    
//    @Modifying
//    @Query(value = "INSERT INTO USERLIST (ULID, USERID, USERPW, USERNAME, JOIN_DATE, LAST_LOGIN_TIME) VALUES (userList_seq.nextval, ?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
//    void insertUser(String userId, String userPw, String userName, LocalDate joinDate, LocalDateTime lastLoginTime);
    

	 

//
//	@Query("SELECT u FROM UserListDTO u WHERE u.userId = ?1")
//	UserListDTO findByUserId(String userId);

	
    
     
    
}
