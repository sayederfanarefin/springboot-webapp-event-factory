package com.avnrsol.eventfactory.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avnrsol.eventfactory.Model.User;

@Repository("userRepository")
public interface UsersRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findById(Long id);
}
