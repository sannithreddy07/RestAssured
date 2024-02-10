package com.restassured.api;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

/*
 given()
 	to add content type, add parameters, add auth, set cookies..etc
 	
 when()
 	defien requests GET, POST , POST, DELETE
 
 then()
 	Validation like status code , extract response, extract headers, cookies, body data.....

 */


public class HTTPrequests {
	
	int id;
	@Test(priority=1)
	
	public void getRequest() {
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		.statusCode(200)
		.body("page",equalTo(2)) // compares the key & valu of first line in json
		.log().all() 	///it will display all content of users list
			;
	}
	@Test(priority=2)
	
	public void postRequest() { //this will create a user
		
		HashMap hm=new HashMap();
		hm.put("name", "jambaris");
		hm.put("job", "leaders");
		
	id=	given()			//this will capture the id 
		.contentType("application/json")
		.body(hm)	
		
		.when()
		.post("https://reqres.in/api/users") /// this will create the new id
		.jsonPath().getInt("id")  //
		
		;
		
		
		// to capture the id above steps in when
		
		//.then()
	//	.statusCode(201)
		//.log().all()
		//; // end of statements
	}
	
	@Test(priority=3, dependsOnMethods="postRequest") // this means it execute only postrequest is success/or user created
public void putRequest() { //this will update a user
		
		HashMap hm=new HashMap();
		hm.put("name", "jambaris");
		hm.put("job", "leaders");
		
		given()		
		.contentType("application/json")
		.body(hm)	
		
		.when()
		.put("https://reqres.in/api/users/"+id)  //concat the fetched id with put request so that the data will be updated
		
		
		
		
		
		.then()
		.statusCode(200)
		.log().all()
		; 
}
}
