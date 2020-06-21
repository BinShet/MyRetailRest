package com.target.myretail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.myretail.exception.MyRetailException;
import com.target.myretail.service.ProductService;
import com.target.myretail.vo.response.ProductResponse;
import com.target.myretail.vo.response.ProductVO;



@RestController
@RequestMapping(path = "/products")
public class ProductDetailController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductVO> getProductById(@PathVariable("id") Long productId) throws MyRetailException
    {		
		long startTime = System.nanoTime();
		ProductVO productvo = new ProductVO();
		productvo = productService.getProductById(productId);
		long endTime = System.nanoTime();
		logger.info(" GET Service Response -- time in ms" + ((endTime-startTime)/1000000));
		logger.debug(" Product Response " + productvo.toString());
		return new ResponseEntity<ProductVO>(productvo, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> updatePrice(@RequestBody ProductVO product,
			@PathVariable("id") Long productId) throws MyRetailException {
		
		long startTime = System.nanoTime();
		if (null== product.getProductId()  ||
				(!(product.getProductId().equals(productId)))) {
			logger.debug("ProductId in URL "+productId+ " product id in body " + product.getProductId() );
			throw new MyRetailException(HttpStatus.BAD_REQUEST.value(),"Product Id in URL and JSON mismatch");
		}
		
		productService.updateProductById(product);
		ProductResponse pr = new ProductResponse(HttpStatus.OK.value(), "Update Successful");
		long endTime = System.nanoTime();
		logger.info(" PUT Service Response -- time in ms" + ((endTime-startTime)/1000000));
		return new ResponseEntity<ProductResponse>(pr, HttpStatus.OK);
	}
	
	@ExceptionHandler(MyRetailException.class)
	public ResponseEntity<ProductResponse> exceptionHandler(MyRetailException ex) {
		logger.debug("Inside Exception  " + ex + " " + ex.getErrorMessage());
		ProductResponse error = new ProductResponse(ex.getErrorCode(),ex.getMessage());
		return new ResponseEntity<ProductResponse>(error,HttpStatus.valueOf(ex.getErrorCode()));
	}

}
