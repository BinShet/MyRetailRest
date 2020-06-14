package com.target.myretail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.target.myretail.entity.CurrentPriceEntity;
import com.target.myretail.entity.ProductDetailEntity;
import com.target.myretail.repository.ProductRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductRepository repository) {

    return args -> {
    	repository.deleteAll();

	    // PreLoad DB Configuration
	    CurrentPriceEntity c1 = new CurrentPriceEntity(123,"USD");
	    log.info("Preloading " + repository.save(new ProductDetailEntity(51514008, c1)));
	    
	    c1 = new CurrentPriceEntity(456,"INR");
	    log.info("Preloading " + repository.save(new ProductDetailEntity(13860428, c1)));	    
	    
	    c1 = new CurrentPriceEntity(13.49,"USD");
	    log.info("Preloading " + repository.save(new ProductDetailEntity(54456119, c1)));
	    
	    c1 = new CurrentPriceEntity(21.49,"USD");	    
	    log.info("Preloading " + repository.save(new ProductDetailEntity(13264003, c1)));
    	     
    };
  }
}