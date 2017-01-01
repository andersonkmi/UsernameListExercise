package org.techstuff.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@ComponentScan()
@EnableTransactionManagement
public class UsernameListMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsernameListMicroserviceApplication.class, args);
	}
}
