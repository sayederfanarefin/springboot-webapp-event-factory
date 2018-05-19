package com.avnrsol.eventfactory.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.Repository.RoleRepository;
import com.avnrsol.eventfactory.Repository.UsersRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.exception.UserAlreadyExistException;
import com.avnrsol.eventfactory.service.interfaces.IUserService;


@Service
@Transactional
public class UserService implements IUserService {
	
	

	@Autowired
	private UsersRepository repository;

	
	@Autowired
	private RoleRepository roleRepository;
	
	

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static final String TOKEN_INVALID = "invalidToken";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String TOKEN_VALID = "valid";

	@Override
	public User registerNewUserAccount(final User accountDto) {
		if (emailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with this email adress: " + accountDto.getEmail());
		}

		// final User user = new User();
		// user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		// user.setEmail(accountDto.getEmail());
		// user.setRoles(accountDto.getRoles());
		// user.setEmployeeDetails(accountDto.getEmployeeDetails());
		// user.setTerritories(accountDto.getTerritories());
		// user.setCreatedCustomers(accountDto.getCreatedCustomers());

		

		
		accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		//System.out.println("->->->->->->->" + u.getEmployeeDetails().getAddress());

		
		return repository.save(accountDto);
	}

	@Override
	public void saveRegisteredUser(final User user) {
		repository.save(user);
	}

	@Override
	public User findUserByEmail(final String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User getUserByID(final long id) {
		return repository.findById(id);
	}

	@Override
	public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
		return false;// passwordEncoder.matches(oldPassword, user.getPassword());
	}

	private boolean emailExist(final String email) {
		return repository.findByEmail(email) != null;
	}

	

	@Override
	public Page<User> findAllUser(int page) {

		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return repository.findAll(request);

	}

	


}