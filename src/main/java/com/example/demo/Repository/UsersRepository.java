package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findById(Long id);
}
