package com.target.myretail.mapper;

import org.springframework.stereotype.Component;

import com.target.myretail.entity.CurrentPriceEntity;
import com.target.myretail.entity.ProductDetailEntity;
import com.target.myretail.exception.MyRetailException;
import com.target.myretail.vo.response.CurrentPriceVO;
import com.target.myretail.vo.response.ProductVO;

@Component
public class ProductMapper {
	
	public ProductVO generateProductResponse(ProductDetailEntity product, String productName) throws MyRetailException {

		ProductVO prodResponse = new ProductVO();
		CurrentPriceVO currentPriceResponse= new CurrentPriceVO();
		try{
			currentPriceResponse.setCurrencyCode(product.getCurrent_price().getCurrency_code());
			currentPriceResponse.setValue(product.getCurrent_price().getPrice());

			prodResponse.setProductId(product.getId());
			prodResponse.setCurrentprice(currentPriceResponse);
			prodResponse.setName(productName);
		}
		catch(Exception e) {
			throw new MyRetailException();
		}
		return prodResponse;
	}
	
	public ProductDetailEntity getProductRepObject(ProductVO productVO) {
		
		ProductDetailEntity product = new ProductDetailEntity();
		CurrentPriceEntity currentPrice = new CurrentPriceEntity();
		
		product.setId(productVO.getProductId());
		currentPrice.setCurrency_code(productVO.getCurrentprice().getCurrencyCode());
		currentPrice.setPrice(productVO.getCurrentprice().getValue());
		product.setCurrent_price(currentPrice);
		return product;
	}


}
