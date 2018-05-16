package com.example.demo.configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Privilege;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.PrivilegeRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UsersRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	

	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	

	// API

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {


		List<Privilege> allPrivileges = new ArrayList<Privilege>();
		
		List<Privilege> adminPrivileges = new ArrayList<Privilege>();
		
		List<Privilege> employeePrivileges = new ArrayList<Privilege>();
		

		Class cls;
		try {
			cls = Class.forName("com.example.demo.configuration.PrivilegeConstants");

			// PrivilegeConstants uuiDd=new PrivilegeConstants();
			Field[] fields = cls.getDeclaredFields(); // get all declared fields
			for (Field field : fields) {
				if (field.getType().equals(String.class)) { // if it is a String field
					// System.out.println("Variable name: "+field.getName());
					field.setAccessible(true);

					// System.out.println("Variable value: "+field.get(cls));

					String prev = field.get(cls).toString();
					allPrivileges.add(createPrivilegeIfNotFound(prev));

					if(prev.contains("USER") || 
							prev.contains("PROJECT") || 
							prev.contains("REVENUE") ||
							prev.contains("EXPENDITURE")||
							prev.contains("ASSIGNEDPROJECTS")||
							prev.contains("ALLOCATION") ||
							prev.contains("COMPANY")) {
						adminPrivileges.add(privilegeRepository.findByName(prev));
						
					}
					
					if(prev.equals(PrivilegeConstants.USER_VIEW) || 
							prev.equals(PrivilegeConstants.ASSIGNEDPROJECTS_VIEW) || 
							prev.equals(PrivilegeConstants.EXPENDITURE_REQUEST)
							) {
						employeePrivileges.add(privilegeRepository.findByName(prev));
						
					}
				}
			}

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		final Role superAdminRole = createRoleIfNotFound(Constants.SUPER_ADMIN, allPrivileges);
		final Role adminRole = createRoleIfNotFound(Constants.ADMIN, adminPrivileges);
		final Role employeeRole = createRoleIfNotFound(Constants.EMPLOYEE, employeePrivileges);

		createUserIfNotFound("superadmin@avnrsol.com", "Super Admin avnrsol", "avnrsol", "test",
				new ArrayList<Role>(Arrays.asList(superAdminRole)));
		
		
		createUserIfNotFound("admin@avnrsol.com", "Just Admin avnrsol", "avnrsol", "test",
				new ArrayList<Role>(Arrays.asList(adminRole)));
		createUserIfNotFound("employee@avnrsol.com", "Employee avnrsol", "avnrsol", "test",
				new ArrayList<Role>(Arrays.asList(employeeRole)));
		
	}

	@Transactional
	private final Privilege createPrivilegeIfNotFound(final String name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			String[] parts = name.split("_");
			privilege.setTag(parts[0]);
			privilege = privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name, final List<Privilege> privileges) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
		}
		role.setPrivileges(privileges);
		role = roleRepository.save(role);
		return role;
	}
	
	

	

	@Transactional
	private final void createUserIfNotFound(final String email, final String firstName, final String lastName,
			final String password, final Collection<Role> roles) {

		System.out.println("---------------adding user init--------------");
		try {
			User user = userRepository.findByEmail(email);
			if (user == null) {
				user = new User();

				user.setPassword(passwordEncoder.encode(password));
				user.setEmail(email);
			
				user.setRoles(roles);
				
				user = userRepository.save(user);
				
			}
			

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	
	

}