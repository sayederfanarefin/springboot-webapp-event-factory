package com.avnrsol.eventfactory.configuration;

import java.lang.reflect.Field;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.avnrsol.eventfactory.Model.Image;
import com.avnrsol.eventfactory.Model.Privilege;
import com.avnrsol.eventfactory.Model.Role;
import com.avnrsol.eventfactory.Model.Serviceo;
import com.avnrsol.eventfactory.Model.ServiceCategory;
import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.Model.Vendor;
import com.avnrsol.eventfactory.Repository.ImageRepository;
import com.avnrsol.eventfactory.Repository.PrivilegeRepository;
import com.avnrsol.eventfactory.Repository.RoleRepository;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.Repository.UsersRepository;
import com.avnrsol.eventfactory.Repository.VendorRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ServiceoRepository serviceRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;
	int serviceCatCount = 2;
	int vendorCount = 2;
	int seviceCount = 5;


	@Value("${file.download.base}")
	private String DOWNLOAD_FOLDER;

	// API

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {


		List<Privilege> allPrivileges = new ArrayList<Privilege>();
		
		List<Privilege> adminPrivileges = new ArrayList<Privilege>();
		
		List<Privilege> employeePrivileges = new ArrayList<Privilege>();
		

		Class cls;
		try {
			cls = Class.forName("com.avnrsol.eventfactory.configuration.PrivilegeConstants");

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
		final Role employeeRole = createRoleIfNotFound(Constants.USER, employeePrivileges);

		createUserIfNotFound("superadmin@avnrsol.com", "Super Admin avnrsol", "avnrsol", "test",
				new ArrayList<Role>(Arrays.asList(superAdminRole)));
		
		
		createUserIfNotFound("admin@avnrsol.com", "Just Admin avnrsol", "avnrsol", "test",
				new ArrayList<Role>(Arrays.asList(adminRole)));
		createUserIfNotFound("user@avnrsol.com", "User avnrsol", "avnrsol", "test",
				new ArrayList<Role>(Arrays.asList(employeeRole)));
		
		

		String[] serviceCats = new String[serviceCatCount];

		for (int x =0; x < serviceCatCount; x++){
			serviceCats[x] = "Category "+ String.valueOf(x);
		}

		for(int i=0; i< vendorCount; i++){
			String vendorName = "Vendor " + String.valueOf(i);
			String vendorEmail = "vendor-" + String.valueOf(i) + "@sayederfanarefin.info";
			Vendor vendor = createVendorIfNotFound(vendorName, vendorEmail, "1123123", "Sylhet", "Bangladesh");
			for (int ii = 0; ii < serviceCatCount; ii++){
				String serviceCategoryName = serviceCats[new Random().nextInt(serviceCats.length)];

				String imageNameCategory = serviceCats[new Random().nextInt(serviceCats.length)] + " Image";

				Image imageCategory = createImageIfNotFound(imageNameCategory, generateRandomWords(100), DOWNLOAD_FOLDER +"/a.jpg");

				ServiceCategory serviceCategory  =  createServiceCategoryIfNotFound(serviceCategoryName, generateRandomWords(100), imageCategory);
				for (int iii=0; iii < seviceCount; iii ++){


					String serviceName = "Service "+ String.valueOf(iii);
					String imageName = "Service "+ String.valueOf(iii) + " Image";

					Image image = createImageIfNotFound(imageName, generateRandomWords(100), DOWNLOAD_FOLDER +"a.jpg");
					createServiceIfNotFound(serviceName, generateRandomWords(500), image, serviceCategory, vendor, generateRandomPrice());
				}
			}
		}
	}

	@Transactional
	 Image createImageIfNotFound(final String name, final String description, String url) {
		
		Image image ; //= imageRepository.findImageByUrl(url);
		//if(image == null) {
			image 	= new Image(name, description , url);
			image = imageRepository.save(image);
		//}
	
		return image;
	}
	
	
	@Transactional
	 Serviceo createServiceIfNotFound(final String name, final String description, final Image image,
			ServiceCategory serviceCategory, Vendor vendor, double price) {
		Serviceo service = serviceRepository.findServiceoByVendor_IdAndServiceCategory_IdAndName(vendor.getId(), serviceCategory.getId(), name);
		if(service == null){
			service  = new Serviceo();
			service.setDescription(description);
			service.setName(name);
			service.addImage(image);
			service.setAvailable(true);
			service.setServiceCategory(serviceCategory);
			vendor.addService(service);
			service.setVendor(vendor);
			service.setPrice(price);
			service = serviceRepository.save(service);
		}
		return service;	
	}
	
	
	@Transactional
	 Vendor createVendorIfNotFound(final String name, String email, String phone , final String city, final String country) {
		Vendor vendor = vendorRepository.findByName(name);
		if(vendor == null) {
			vendor = new Vendor();
			vendor.setName(name);
			vendor.setCity(city);
			vendor.setCountry(country);
			vendor.setEmail(email);
			vendor.setPhone(phone);
			vendorRepository.save(vendor);
		}
			return vendor;	
	}
	
	@Transactional
	 ServiceCategory createServiceCategoryIfNotFound(final String name, final String description, final Image image) {

		ServiceCategory serviceCategory  = serviceCategoryRepository.findByName(name);
		if(serviceCategory ==null) {
			serviceCategory = new ServiceCategory();
			serviceCategory.setDescription(description);
			serviceCategory.setName(name);
			serviceCategory.addImage(image);
			serviceCategory = serviceCategoryRepository.save(serviceCategory);
		}
		return serviceCategory;	
	}
	
	
	@Transactional
	  Privilege createPrivilegeIfNotFound(final String name) {
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
	  Role createRoleIfNotFound(final String name, final List<Privilege> privileges) {
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

	public static String generateRandomWords(int numberOfWords)
	{
		String[] randomStrings = new String[numberOfWords];
		Random random = new Random();
		for(int i = 0; i < numberOfWords; i++)
		{
			char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
			for(int j = 0; j < word.length; j++)
			{
				word[j] = (char)('a' + random.nextInt(26));
			}
			randomStrings[i] = new String(word);
		}

		return String.join(" ", randomStrings);

	}
	public static double generateRandomPrice(){
		double rangeMin = 10.01;
		double rangeMax = 10000;
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		return randomValue;
	}

}