package com.avnrsol.eventfactory.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Model.Charge;
import com.avnrsol.eventfactory.Model.Order;
import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.Repository.ChargeRepository;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.service.UserService;
import com.avnrsol.eventfactory.service.VendorService;

@Controller
public class StoreController {

	@Autowired
	private UserService userService;

	@Autowired
	private ServiceoRepository serviceoRepository;

	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;

	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private ChargeRepository chargeRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@CookieValue(value = "test_cookie", defaultValue = "hello") String fooCookie) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		modelAndView.addObject("vendors", vendorService.findAllVendor(0));
		
		System.out.println("cookie ->->->->->->->->->->->->-> "+ fooCookie);
		
		return modelAndView;
	}

	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public ModelAndView service(@RequestParam("id") Long id, @CookieValue(value = "test_cookie", defaultValue = "hello") String fooCookie) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("single-product");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		modelAndView.addObject("serv", serviceoRepository.findServiceoById(id));
		modelAndView.addObject("related", serviceoRepository.findTop4ServiceoByServiceCategory_Id(serviceoRepository.findServiceoById(id).getServiceCategory().getId()));
		
		System.out.println("cookie ->->->->->->->->->->->->-> "+ fooCookie);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/serviceCategory", method = RequestMethod.GET)
	public ModelAndView serviceCategory(@RequestParam("id") Long id, @CookieValue(value = "test_cookie", defaultValue = "hello") String fooCookie) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("service-category");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		modelAndView.addObject("category", serviceCategoryRepository.findById(id));
		return modelAndView;
	}
	
	@RequestMapping(value = "/directory", method = RequestMethod.GET)
	public ModelAndView dategory(@RequestParam("id") Long id, @CookieValue(value = "test_cookie", defaultValue = "hello") String fooCookie) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("store-directory");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
			
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public ModelAndView myProfile(@Valid Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("my-account");
		User u = userService.findUserByEmail(principal.getName());
		System.out.println(u.getFirstName());
		modelAndView.addObject("userx", u);
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public ModelAndView faq() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("faq");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/termsAndConditions", method = RequestMethod.GET)
	public ModelAndView termsAndCondition() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("terms-and-conditions");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cart() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cart");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkOut(@Valid Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("checkout");
		
		Order order = new Order();
		modelAndView.addObject("order", order);
		
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		modelAndView.addObject("userx", userService.findUserByEmail(principal.getName()));
		List<Charge> charges = chargeRepository.findAll();
		modelAndView.addObject("charges", charges.get(0));
		return modelAndView;
	}
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("about");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/orderSuccess", method = RequestMethod.GET)
	public ModelAndView orderSuccess() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("orderSuccess");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/orderFailed", method = RequestMethod.GET)
	public ModelAndView orderFailed() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("orderFailed");
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}
	
}
