package com.photoApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.photoApp.entity.UserEntity;


public interface UsersRepository extends CrudRepository<UserEntity,Long> {

	
	public UserEntity findByEmail(String email);
	
	@Query("select u from UserEntity u where email=:email")
	public UserEntity findUserByEmail(@Param("email") String email);
}
