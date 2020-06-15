package com.target.myretail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.target.myretail.controller.ProductDetailController;
import com.target.myretail.service.ProductService;
import com.target.myretail.vo.response.CurrentPriceVO;
import com.target.myretail.vo.response.ProductVO;



@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductDetailController.class, secure = false)
public class MyRetailApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService productService;


	@Test
	public void getProductDetailsTest() throws Exception {
		
		ProductVO product = new ProductVO();
		CurrentPriceVO currentPrice = new CurrentPriceVO();
		
		product.setProductId(new Long(13860428));
		product.setName("The Big Lebowski (Blu-ray)");
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(13.49);
		product.setCurrentprice(currentPrice);
						
		Mockito.when(productService.getProductById(new Long(13860428)))
				.thenReturn(product);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/13860428")
				.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result =  mvc.perform(requestBuilder).andReturn();
		System.out.println("result "+result.getResponse().getContentAsString());
		
		String expected = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);


	}

}