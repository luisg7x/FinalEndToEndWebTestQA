package finaltc;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GetTweet;
import resources.RestBase;
import static io.restassured.RestAssured.given;

public class RestTwiterGetSingleTweet extends RestBase{

	private RequestSpecification req;
	private ResponseSpecification res;
	
	@BeforeTest()
	public void getRequestBase() throws IOException {
		this.req = given().spec(requestSpecification());
	}
	
	@Test
	public void getSingleTweet() throws IOException {
		req = req
				.header("Authorization","Bearer" + " " + getValue("Bearer"))
				.queryParams("tweet.fields", "created_at,attachments")
				.queryParams("expansions","author_id")
				.pathParam("id", "1345889499625222145");
		
		res = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		/*String response = given().spec(req).get("/2/tweets/{id}")
				.then().spec(res).extract().response().asString();*/
		
		GetTweet response = given()
				.spec(req)
				.get("/2/tweets/{id}")
				.then()
				.spec(res)
				.extract()
				.as(GetTweet.class);

		
		System.out.println(response.getData().getAuthor_id());
		
		//System.out.println(response);
	}
	
}
