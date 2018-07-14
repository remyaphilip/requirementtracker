package com.proman.api.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proman.api.user.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	public User findByEmail(String email);
	public List<User> findByOrganisation(String organisation);

}
