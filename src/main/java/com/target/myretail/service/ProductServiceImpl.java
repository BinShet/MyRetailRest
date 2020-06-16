package com.target.myretail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.target.myretail.entity.ProductDetailEntity;
import com.target.myretail.exception.MyRetailException;
import com.target.myretail.mapper.ProductMapper;
import com.target.myretail.repository.ProductRepository;
import com.target.myretail.vo.response.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductMapper prodMapper;
	
	@Value("${product-api-endpoint}")
	private String apiEndpointURL;
	
	@Value("${product-api-exclusion}")
	private String apiEndpointExcl;
	
	@Override
	public ProductVO getProductById(Long productId) throws MyRetailException {
		
		ProductDetailEntity product = new ProductDetailEntity();
		String prodName = "";
		
		try {
			//retrieve from MongoDB
			product = productRepository.findProductByproductId(productId);
			if(product == null) {
				logger.debug("Product Not Found Exception while fetching product data from DB ");
				throw new MyRetailException(HttpStatus.NOT_FOUND.value(),"Product not found in DB");
			}
		} catch(MyRetailException e) {
			throw e; 
		}
		prodName = getRemoteProductName(productId);
		
		return prodMapper.generateProductResponse(product,prodName);
	}
	

	@Override
	public void updateProductById(ProductVO productVO) throws MyRetailException {
		
		logger.debug("In Product Service--updateProductById");
		
		if(productRepository.existsById(productVO.getProductId())) {
			ProductDetailEntity product =prodMapper.getProductRepObject(productVO);			
			try {
				productRepository.save(product);
			} catch (Exception exception) {
				logger.debug("Error while updating product ");
				throw new MyRetailException(HttpStatus.SERVICE_UNAVAILABLE.value(),"Product Update Failed");
			}
		} else {
			logger.debug("Product Not Found in DB while doing update ");
			throw new MyRetailException(HttpStatus.NOT_FOUND.value(),"Product not found for update");
		}
		
		
	}
	

	private String getRemoteProductName(Long productId) throws MyRetailException {
		
		logger.debug("Getting Product Name from remote call ");
		RestTemplate restTemplate = new RestTemplate();
		String itemTitle = "";
		
		ResponseEntity<JsonNode> response = null;
		try {
           response =
                    restTemplate.exchange(apiEndpointURL
                    		+ productId
                    		+ apiEndpointExcl,
                            HttpMethod.GET, null, JsonNode.class);
            
            logger.debug("JSON Response from Remote Client  :" + response);            
        } catch (RestClientException e) {
			logger.debug("Product API unavailable  :" + apiEndpointURL + productId);
			throw new MyRetailException(HttpStatus.NOT_FOUND.value() ,"Product Remote API unavailable");
		} catch (Exception e) {
        	logger.debug("Exception in getRemoteProductName ");
            e.printStackTrace();
        }
		
		if( response != null && response.hasBody() && !response.getBody().isNull()) {
        	JsonNode map = response.getBody();
        	if( map.path("product").path("item").path("product_description").path("title") != null) { 
        		itemTitle = map.get("product").get("item").get("product_description").get("title").asText();
        	} else {
				logger.debug("Product title JSON value Unavailable in Product API");
				throw new MyRetailException(HttpStatus.NO_CONTENT.value() ,"Title does not exists for this product" );
        	}
        } else {
			logger.debug("Product Unavailable in API");
			throw new MyRetailException(HttpStatus.NO_CONTENT.value() ,"Product does not exist" );
    	}
		
		return itemTitle;
	}

}
