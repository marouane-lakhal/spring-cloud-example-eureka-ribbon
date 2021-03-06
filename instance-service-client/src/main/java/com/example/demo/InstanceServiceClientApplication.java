package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class InstanceServiceClientApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}	
	
	public static void main(String[] args) {
		SpringApplication.run(InstanceServiceClientApplication.class, args);
	}

}

@RestController
class instanceServiceClient {

	@Autowired
	private RestTemplate restTemplate;


	@RequestMapping(method = RequestMethod.GET, value = "/instance")
	public String getServiceInstance() {
		return restTemplate.getForObject("http://instance-service/instanceId", String.class);
	}
	
}
