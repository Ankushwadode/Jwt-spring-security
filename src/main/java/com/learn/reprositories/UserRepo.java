package com.learn.reprositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learn.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	User findFirstByEmail(String email);

}
