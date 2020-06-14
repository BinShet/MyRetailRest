package com.target.myretail.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="productprice")
public class ProductDetailEntity {
	
	@Id
	private long productId;
	
	private CurrentPriceEntity current_price;
	
	public ProductDetailEntity() {}
	
	public ProductDetailEntity(long id, CurrentPriceEntity current_price) {	
		this.productId = id;
		this.current_price = current_price;
	}
	

	public long getId() {
		return productId;
	}

	public void setId(long id) {
		this.productId = id;
	}

	public CurrentPriceEntity getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(CurrentPriceEntity current_price) {
		this.current_price = current_price;
	}
	
	 @Override
	    public String toString() {
	        return "Product{" +
	                ", id='" + productId + '\'' +
	                ", Price='" + current_price.getPrice() + '\'' + 
	                 ", Currency Code='" + current_price.getCurrency_code() + '\'' +
	                '}';
	 }

}
