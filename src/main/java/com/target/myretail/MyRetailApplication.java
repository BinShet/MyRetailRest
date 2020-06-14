package com.target.myretail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.target.myretail.entity.CurrentPriceEntity;
import com.target.myretail.entity.ProductDetailEntity;
import com.target.myretail.repository.ProductRepository;


@SpringBootApplication
public class MyRetailApplication implements CommandLineRunner {
	
	@Autowired
	private ProductRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

	    repository.deleteAll();

	    // save a couple of customers
	    CurrentPriceEntity c1 = new CurrentPriceEntity(123,"USD");
	    repository.save(new ProductDetailEntity(51514008, c1));
	    c1 = new CurrentPriceEntity(456,"INR");
	    repository.save(new ProductDetailEntity(13860428, c1));
	    c1 = new CurrentPriceEntity(13.49,"USD");
	    repository.save(new ProductDetailEntity(54456119, c1));
	    c1 = new CurrentPriceEntity(21.49,"USD");
	    repository.save(new ProductDetailEntity(13264003, c1));
	   
	  }

}
