package com.sudha.domains;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	
	@RequestMapping("/getusers")
	public List<User> getUsers(){
		
		List<User> listUsers = new ArrayList<User>();
		for(int i=0;i<10;i++){
			User user = new User();
			user.setFirstName("First Name "+i);
			user.setLastName("Last Name "+i);
			user.setGender("Male");
			listUsers.add(user);
		}
		System.out.println("==========");
		return listUsers;
	}
	
	@RequestMapping(value = "/user")
	public User getUser(){
		
			User user = new User();
			user.setFirstName("First Name" );
			user.setLastName("Last Name ");
			user.setGender("Male");
		System.out.println("======22====");
		return user;
	}
	
	@RequestMapping("/abc")
    public String welcome() {//Welcome page, non-rest
		System.out.println("!!!!!!!");
        return "Welcome to RestTemplate Example.";
    }
	
	@RequestMapping(value = "/saveuser/{clientid}",  method= RequestMethod.POST)
	public User saveuser(@RequestBody User user, @RequestParam("clientname") String client, @PathVariable("clientid") String clientid11){
		System.out.println("save user-->"+client+"=="+clientid11);
		return user;
	}
	
	@RequestMapping(value = "/saveappuser",  method= RequestMethod.POST)
	public User saveuserdetails(@RequestBody User user){
		System.out.println("save userdetails-->");
		return user;
	}
	
	@RequestMapping(value = "/basicauth",  method= RequestMethod.POST)
	public User basicAuthCheck(@RequestBody User user, @RequestHeader("authorization") String auth){
		System.out.println("basicAuthCheck-->"+auth);
		byte[] decoder = Base64.getDecoder().decode(auth.split("\\s+")[1]);
		System.out.println(new String(decoder));
		user.setFirstName(new String(decoder));
		return user;
	}
	
}
