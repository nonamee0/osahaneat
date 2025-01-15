package com.cybersoft.osahaneat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybersoft.osahaneat.entity.Users;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	List<Users> findByUserNameAndPassword(String userName, String password);
	Users findByUserName(String userName);
}
