package testcases;

import pojo.Product;
import routes.Routes;
import utils.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;


public class ProductTests extends BaseClass{

	//1) Test to retrieve all the products
	@Test
	public void testGetAllProducts()
	{
		given()
		
		.when()
			.get(Routes.GET_ALL_PRODUCTS)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0));
			//.log().body();
	}
	
	//2) Test to retrieve single product by id
	@Test
	public void testGetProductById()
	{
		int productId=configReader.getIntProperty("productId");
		
		given()
			.pathParam("id", productId)
		.when()
			.get(Routes.GET_PRODUCT_BY_ID)
		.then()
			.statusCode(200)
			.log().body();
	}
	
	//3) Test to retrieve a limited number of products
	@Test
	public void testGetLimitedProducts()
	{
		
		given()
			.pathParam("limit", 3)// here limit we pass as a path parameter because "limit={limit}" this is already present in "GET_PRODUCT_BY_ID" route.
		.when()
			.get(Routes.GET_PRODUCTS_WITH_LIMIT)
		.then()
			.statusCode(200)
			.log().body()
			.body("size()", equalTo(3));
	}
		
	//4) Test to retrieve products sorted in descending order
	@Test
	public void testGetSortedProductsDesc()
	{
		
		Response response=given()
			.pathParam("order", "desc")
		.when()
			.get(Routes.GET_PRODUCTS_SORTED)
		.then()
			.statusCode(200)
			.extract().response();
		
		List<Integer> productIds=response.jsonPath().getList("id",Integer.class); //Get list of all id's and all are of type integer.
		assertThat(isSortedDescending(productIds), is(true));
	}
			
	//5) Test to retrieve products sorted in ascending order
	@Test
	public void testGetSortedProductsAsc()
	{
		
		Response response=given()
			.pathParam("order", "asc")
		.when()
			.get(Routes.GET_PRODUCTS_SORTED)
		.then()
			.statusCode(200)
			.extract().response();
		
		List<Integer> productIds=response.jsonPath().getList("id",Integer.class); //Get list of all id's and all are of type integer.
		assertThat(isSortedAscending(productIds), is(true));
	}
	
	//6) Test to get all categories of products
	@Test
	public void testGetAllCategories()
	{
		given()
		
		.when()
			.get(Routes.GET_ALL_CATEGORIES)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0));
	}

	//7) Test to get a product by specific category
		@Test
		public void testGetProductsByCategory()
		{
			given()
				.pathParam("category", "electronics")
			.when()
				.get(Routes.GET_PRODUCTS_BY_CATEGORY)
			.then()
				.statusCode(200)
				.body("size()", greaterThan(0))
				.body("category", everyItem(notNullValue()))
				.body("category", everyItem(equalTo("electronics")))
				.log().body();
		}
		
	//8) Add new Product
	
	@Test
	public void testAddNewProduct()
	{
		Product newProduct=Payload.productPayload();
		
		int productId=given()
		 //newProduct is in pojo form so to pass this payload we need to serialization(pojo to json conversion)
			.contentType(ContentType.JSON)
			.body(newProduct)
		.when()
			.post(Routes.CREATE_PRODUCT)
		.then()
			.log().body()
			.statusCode(201)
			.body("id", notNullValue())
			.body("title", equalTo(newProduct.getTitle()))
			.extract().jsonPath().getInt("id"); //Extracting id from response body.
		
		System.out.println(productId);
			// As this productId is not storing in database. We cant use for updation and deletion method. If you rerun this method, Id 21 same id will return.
	}
	
	//9) Update a product
	
	@Test
	public void testUpdateProduct()
	{
		int productId=configReader.getIntProperty("productId");
		
		Product updatedPayload= Payload.productPayload();
		
		given()
			.contentType(ContentType.JSON)
			.body(updatedPayload)
			.pathParam("id", productId)
		.when()
			.put(Routes.UPDATE_PRODUCT)
		.then()
			.log().body()
			.statusCode(200)
			.body("title", equalTo(updatedPayload.getTitle()));
	}
	
	//10) Delete a product
	
	@Test
	public void testDeleteProduct()
	{
		int productId=configReader.getIntProperty("productId");
		
		given()
			.pathParam("id", productId)
		.when()
			.delete(Routes.DELETE_PRODUCT)
		.then()
			.statusCode(200);
			
	}
}
