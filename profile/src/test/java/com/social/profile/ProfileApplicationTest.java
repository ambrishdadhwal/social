package com.social.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.social.domain.Profile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProfileApplicationTest
{

	private static final String API_ROOT = "https://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

	@Test
	void contextLoads()
	{
	}

	@Test
	void whenCreateNewBook_thenCreated()
	{
		Profile book = createFBUser();
		Response response = RestAssured.given()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(book)
			.post(API_ROOT);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());

		// logs the request to console
		RestAssured.given()
			.when()
			.get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1")
			.then()
			.log()
			.all();

		RestAssured.given()
			.when()
			.get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1")
			.then().extract().contentType();
	}

	private Profile createFBUser()
	{
		return Profile.builder()
			.firstName("Test")
			.lastName("Test")
			.email("test@test.com")
			.id(1L)
			.build();
	}

	private String createBookAsUri(Profile user)
	{
		Response response = RestAssured.given()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(user)
			.post(API_ROOT);

		return API_ROOT + "/" + response.jsonPath().get("id");
	}

}
