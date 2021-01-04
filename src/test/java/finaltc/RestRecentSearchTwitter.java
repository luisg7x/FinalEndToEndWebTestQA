package finaltc;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.seratch.signedrequest4j.HttpMethod;
import com.github.seratch.signedrequest4j.OAuthAccessToken;
import com.github.seratch.signedrequest4j.OAuthConsumer;
import com.github.seratch.signedrequest4j.OAuthRealm;
import com.github.seratch.signedrequest4j.SignedRequest;
import com.github.seratch.signedrequest4j.SignedRequestFactory;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.RestBase;

public class RestRecentSearchTwitter extends RestBase{

	private RequestSpecification req;
	private ResponseSpecification res;
	
	@BeforeTest()
	public void getRequestBase() throws IOException {
		this.req = given().spec(requestSpecification());
	}
	
	@Test
	public void getRecentSearchTwitter() throws IOException, InterruptedException, ExecutionException {
		
		req = req
				.auth()
				.oauth(getValue("consumerKey"), getValue("consumerSecret"), getValue("accessToken"), getValue("secretToken")) //add scribe-java dependecy 2.5.3 //https://www.qaautomation.co.in/2019/02/automating-twitter-api-using-rest.html
				.queryParams("query", "nyc")
				.queryParams("max_results","15");
		
		res = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		
		String response = given()
				.spec(req)
				.get("/2/tweets/search/recent")
				.then()
				.spec(res)
				.extract()
				.response()
				.asString();

		System.out.println(response);


	}
	
}
