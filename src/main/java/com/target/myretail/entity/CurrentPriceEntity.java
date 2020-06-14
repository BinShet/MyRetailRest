package com.target.myretail.entity;

public class CurrentPriceEntity {
		
		private double price;
		private String currency_code;
		
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getCurrency_code() {
			return currency_code;
		}
		public void setCurrency_code(String currency_code) {
			this.currency_code = currency_code;
		}
		public CurrentPriceEntity(double price, String currency_code) {
			this.price = price;
			this.currency_code = currency_code;
		}
		public CurrentPriceEntity() {
			// TODO Auto-generated constructor stub
		}

		
	}

