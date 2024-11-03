package com.lrtech.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner  {

	@Autowired
	private PasswordEncoder encoder;
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
  public void run(String... args) throws Exception {
    System.out.println("funcionando Mero");
		System.out.println(encoder.encode("123456"));
		
  }
}
