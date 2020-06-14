# MyRetailRest RESTful service
----------------------------
This Case study represent a Proof of Concept for a Product Api, which aggregate product data from multiple sources and return it as JSON to the caller.

Operations
•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com). Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 
•	Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.  

Tech Stack 
-----------
Springboot, Maven, Mockito/Junit, RestTemplate, MongoDB.

Prerequisites
-------------
Java 1.8
MongoDb Database
Web Services Testing Engine like Postman

Target Service: https://redsky.target.com/v2/pdp/tcin/{id} 

To run the application 
-------------------------
Start the MongoDB server.
Download the jar. Navigate to the jar folder and run mvn spring-boot:run 

•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 

Request->
	URL - http://localhost:8081/products/13860428
	Request Method: GET
Sample Response->
	{
	    "id": 51514008,
	    "name": "SquareTrade 2 Year Electronics Protection Plan",
	    "current_price": {
	        "value": 456,
	        "currency_code": "USD"
	    }
	}

•	Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.

Request->
	URL - http://localhost:8081/products/13860428
	Request Method: PUT
	Request Body: 
	{
	    "id": 51514008,
	    "name": "SquareTrade 2 Year Electronics Protection Plan",
	    "current_price": {
	        "value": 99999,
	        "currency_code": "USD"
	    }
	}
Sample Response ->
	{
	    "statusCode": 200,
	    "message": "Update Successful"
	}

