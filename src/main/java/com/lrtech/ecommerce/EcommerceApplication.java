package com.lrtech.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = " Ecommerce Api",version = "1",description = "API desenvolvida para colocar em praticas meus estudos"))
@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
  public void run(String... args) throws Exception {
    System.out.println("funcionando Mero");
		
		
  }
}
	