package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import payloads.Payload;
import pojo.Product;
import routes.Routes;

public class ProductDataDrivenTest extends BaseClass
{
 
	@Test(dataProvider="jsonDataProvider", dataProviderClass=utils.DataProviders.class)
	public void testAddNewProduct(Map<String, String> data)
	{
		
		String title= data.get("title");
		double price= Double.parseDouble(data.get("price"));
		String category= data.get("category");
		String image= data.get("image");
		String description= data.get("description");
		
		/*As we are not getting data from payload. Whatever data we are capturing we have to send this data with post request.So we have to convert
		data into pojo form*/
		
		Product newProduct=new Product(title, price, description, image, category);
		
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
		
		System.out.println("productId========>"+productId);
		
		//Delete Product
		given()
			.pathParam("id", productId)
		.when()
			.delete(Routes.DELETE_PRODUCT)
		.then()
			.statusCode(200);
		
		System.out.println("Deleted productId========>"+productId);
	}
	
	
}
