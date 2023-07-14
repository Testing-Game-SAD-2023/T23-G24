package com.g24.authentication.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.g24.authentication.model.entity.User;


public interface UserRepository extends JpaRepository<User, Long>
{

	User findByEmail(String email);

	@Modifying
	@Query("update User u set u.password = :password where u.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") Long id);

	@Modifying
	@Query("update User u set u.enabled = true where u.id = :id")
	void enable(@Param("id") Long id);

	List<User> findByUniversity(String university);
	List<User> findByDegreeCourse(String degreeCourse);
	List<User> findByEnabledTrue();
}