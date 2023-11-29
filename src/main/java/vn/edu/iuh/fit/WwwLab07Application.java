package vn.edu.iuh.fit;

import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.models.Employee;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.repositories.CustomerRepository;
import vn.edu.iuh.fit.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.sql.Array;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@SpringBootApplication
public class WwwLab07Application {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductPriceRepository productPriceRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(WwwLab07Application.class, args);
	}

//	@Bean
//	CommandLineRunner createSampleProducts(){
//		return args -> {
//			Faker faker =new Faker();
//			Device devices = faker.device();
//			for (int i = 0; i < 200; i++) {
//				Product product =new Product(
//						devices.modelName(),
//						faker.lorem().paragraph(30),
//						"piece",
//						devices.manufacturer(),
//						ProductStatus.ACTIVE
//				);
//				productRepository.save(product);
//			}
//		};
//	}

////	Product & ProductPrice
//	@Bean
//	@Transactional
//	CommandLineRunner createSampleProducts(){
//		return args -> {
//			Faker faker =new Faker();
//			Random rnd = new Random();
//			Device devices = faker.device();
//
//			for (int i = 0; i < 200; i++) {
//				Product product =new Product(
//						devices.modelName(),
//						faker.lorem().paragraph(30),
//						"piece",
//						devices.manufacturer(),
//						ProductStatus.ACTIVE
//				);
//				ProductPrice price = new ProductPrice(product, LocalDateTime.now(), rnd.nextDouble(), "note");
//				productRepository.save(product);
//				productPriceRepository.save(price);
//			}
//		};
//	}

//	@Bean
//	@Transactional
//	CommandLineRunner createSampleProducts(){
//		return args -> {
//			Faker faker =new Faker();
//			Random rnd = new Random();
//			Device devices = faker.device();
//
//			for (int i = 0; i < 200; i++) {
//				Customer customer = new Customer(
//						faker.name().fullName(),
//						faker.internet().emailAddress(),
//						faker.phoneNumber().cellPhone(),
//						faker.address().fullAddress()
//				);
//				customerRepository.save(customer);
////			}
//		};
//	}


//	@Bean
//	@Transactional
//	CommandLineRunner createSampleProducts(){
//		return args -> {
//			Faker faker =new Faker();
//			Random rnd = new Random();
//			for (int i = 0; i < 200; i++) {
//				Employee employee = new Employee(
//						faker.name().fullName(),
//						faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//						faker.internet().emailAddress(),
//						faker.phoneNumber().cellPhone(),
//						faker.address().fullAddress(),
//						EmployeeStatus.values()[rnd.nextInt(EmployeeStatus.values().length)]
//				);
//				employeeRepository.save(employee);
//			}
//		};
//	}



}
