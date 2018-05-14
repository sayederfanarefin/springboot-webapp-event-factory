package com.example.demo.configuration;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	//
	// @Autowired
	// private PasswordEncoder passwordEncoder;

	// API

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		final Role adminRole = createRoleIfNotFound("ADMIN");

		// == create initial user
		//createUserIfNotFound("test@test.com", "Test", "Test", "test123", adminRole);

	}

	@Transactional
	private final void createUserIfNotFound(final String email, final String firstName, final String lastName,
			final String password, final Role roles) {

		try {
			User user = userRepository.findByEmail(email);
			if (user == null) {
				user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setPassword(passwordEncoder.encode(password));
				user.setEmail(email);
				user.setActive(1);
				user.setRole(roles);
				user = userRepository.save(user);

			}
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name) {
		Role role = roleRepository.findByRole(name);
		if (role == null) {
			role = new Role(name);
		}

		role = roleRepository.save(role);
		return role;
	}
}