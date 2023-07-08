package com.pos.posspringbackend;

import com.pos.posspringbackend.auth.request.RegisterRequest;
import com.pos.posspringbackend.auth.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.pos.posspringbackend.user.enumerated.Role.ADMIN;
import static com.pos.posspringbackend.user.enumerated.Role.INVENTORY_CLERK;

@SpringBootApplication
public class PointOfSalesAndInventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointOfSalesAndInventoryManagementApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthenticationService service
//	) {
//		return args -> {
//			RegisterRequest admin = RegisterRequest.builder()
//					.firstName("Admin")
//					.lastName("Admin")
//					.email("admin@mail.com")
//					.password("password")
//					.role(ADMIN)
//					.build();
//			System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//			RegisterRequest inventoryClerk = RegisterRequest.builder()
//					.firstName("Admin")
//					.lastName("Admin")
//					.email("manager@mail.com")
//					.password("password")
//					.role(INVENTORY_CLERK)
//					.build();
//			System.out.println("inventoryClerk token: " + service.register(inventoryClerk).getAccessToken());
//		};
//	}
}
