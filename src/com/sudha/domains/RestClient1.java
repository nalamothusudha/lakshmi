package com.sudha.domains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class RestClient1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		/*RestTemplate restTemplate = new RestTemplate();
		User user =  restTemplate.getForObject("http://localhost:8084/SpringMvcREST/user",
				User.class);
		
		System.out.println("======="+user.getFirstName());
		
		User userdetails = new User();
		userdetails.setFirstName("srilakshmi");
		
		RestTemplate restTemplate2 = new RestTemplate();
		User user2 =  restTemplate2.postForObject("http://localhost:8084/SpringMvcREST/saveappuser",
				userdetails, User.class);
		
		System.out.println("======="+user2.getFirstName());
	
*/		
		RestTemplate restTemplate3 = new RestTemplate();
		User details = new User();
		details.setFirstName("srilakshmi");
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", header);
		ResponseEntity<String> response=restTemplate3.exchange(
				"http://localhost:8084/SpringMvcREST/getusers",HttpMethod.GET, entity, String.class);
	//	System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		
	//	System.out.println(response);
	}

}
