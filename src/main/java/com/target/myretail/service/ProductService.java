package com.target.myretail.service;

import com.target.myretail.exception.MyRetailException;
import com.target.myretail.vo.response.ProductVO;

public interface ProductService {
	
	public ProductVO getProductById(Long productId) throws MyRetailException;
	
	public void updateProductById(ProductVO productVO) throws MyRetailException;

}
