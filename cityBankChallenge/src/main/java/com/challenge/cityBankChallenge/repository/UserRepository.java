package com.challenge.cityBankChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.challenge.cityBankChallenge.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByLogin(String username);

}
