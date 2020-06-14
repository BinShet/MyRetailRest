package com.target.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.target.myretail.entity.ProductDetailEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductDetailEntity, Long> {
	
	/**
	 * @param productId
	 * @return
	 */
	public ProductDetailEntity findProductByproductId(long productId);

}
