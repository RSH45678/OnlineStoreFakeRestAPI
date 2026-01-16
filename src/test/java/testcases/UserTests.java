package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.User;
import routes.Routes;

public class UserTests extends BaseClass
{
	//1) Get all the users
	@Test
	public void testGetAllUsers()
	{
		given()
		
		.when()
			.get(Routes.GET_ALL_USERS)
		.then()
			.statusCode(200)
			.log().body()
			.contentType(ContentType.JSON)
			.body("size()", greaterThan(0));
	}
	
	//2) Get User by id
	@Test
	public void testGetUserById()
	{
		int userId=configReader.getIntProperty("userId");
		given()
			.pathParam("id", userId)
		.when()
			.get(Routes.GET_USER_BY_ID)
		.then()
			.statusCode(200)
			.log().body();
	}
	
	//3) Get limited number of users
	@Test
	public void testGetUserWithLimit()
	{
		given()
			.pathParam("limit", 3)
		.when()
			.get(Routes.GET_USERS_WITH_LIMIT)
		.then()
			.statusCode(200)
			.body("size()", equalTo(3))
			.log().body();
	}
	
	//4) Get users sorted in descending order
	@Test
	public void testGetSortedUsersDesc()
	{
		
		Response response=given()
			.pathParam("order", "desc")
		.when()
			.get(Routes.GET_USERS_SORTED)
		.then()
			.statusCode(200)
			.extract().response();
		
		List<Integer> userIds=response.jsonPath().getList("id",Integer.class); //Get list of all user id's and all are of type integer.
		assertThat(isSortedDescending(userIds), is(true));
	}
	
	//5) Get users sorted in ascending order
	@Test
	public void testGetSortedUsersAsc()
	{
		
		Response response=given()
			.pathParam("order", "asc")
		.when()
			.get(Routes.GET_USERS_SORTED)
		.then()
			.statusCode(200)
			.extract().response();
		
		List<Integer> userIds=response.jsonPath().getList("id",Integer.class); //Get list of all user id's and all are of type integer.
		assertThat(isSortedAscending(userIds), is(true));
	}
	
	//6) Create new user
	@Test
	public void testCreateUser()
	{
		User newUser=Payload.userPayload();
		int id=given()
			.contentType(ContentType.JSON)
			.body(newUser)
		.when()
			.post(Routes.CREATE_USER)
		.then()
			.log().body()
			.statusCode(201)
			.body("id", notNullValue())
			.extract().jsonPath().getInt("id");
		
		System.out.println("generated User Id======>"+ id);
	}
	
	//7) Update user
	@Test
	public void testUpdateUser()
	{
		int userId=configReader.getIntProperty("userId");
		User updateUser=Payload.userPayload();
		
		given()
			.contentType(ContentType.JSON)
			.pathParam("id", userId)
			.body(updateUser)
		.when()
			.put(Routes.UPDATE_USER)
		.then()
			.log().body()
			.statusCode(200)
			.body("username", equalTo(updateUser.getUsername()));
			
	}
	
	//8) Delete user
	@Test
	void testDeleteUser()
	{

		int userId=configReader.getIntProperty("userId");
		
		given()
			.pathParam("id", userId)
		.when()
			.delete(Routes.DELETE_USER)
		.then()
			.statusCode(200);
		}
				
}
