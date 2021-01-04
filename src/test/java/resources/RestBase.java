package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.Properties;

import com.github.seratch.signedrequest4j.HttpMethod;
import com.github.seratch.signedrequest4j.OAuthAccessToken;
import com.github.seratch.signedrequest4j.OAuthConsumer;
import com.github.seratch.signedrequest4j.SignedRequest;
import com.github.seratch.signedrequest4j.SignedRequestFactory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class RestBase {

	//making static for prevent create another instance when there are 2 or 3 execution with the data set
		//and this make posible have the logs of all execeution on one file and no get replaced for the last 
		private static RequestSpecification req;
		
		public RequestSpecification requestSpecification() throws IOException {
			 //this if fix when there are a 2ble execution usign datasets on cucumber, that the last execeution replace the log of the last  of that exceution
			if (req==null) {
				
				PrintStream log = new PrintStream(new FileOutputStream("twitter.txt"));
				
				req = new RequestSpecBuilder().setBaseUri(getValue("baseUrlTwiter"))
						//.addPathParam("id", "1191080684590424066")
						.addFilter(RequestLoggingFilter.logRequestTo(log))
						.addFilter(ResponseLoggingFilter.logResponseTo(log))
						//.setContentType(ContentType.JSON)
						.build();
				 
				 return req;
				 
			}
			
			return req;
			
		}
		
		public String getOAuth1() throws IOException {
			String url = "https://api.twitter.com/1.1/account/verify_credentials.json";
			
			OAuthConsumer consumer = new OAuthConsumer(getValue("consumerKey"), getValue("consumerSecret"));
			OAuthAccessToken accessToken = new OAuthAccessToken(getValue("accessToken"), getValue("secretToken"));

			//OAuthRealm realm = new OAuthRealm(myRealm);
			SignedRequest request = 
			                   SignedRequestFactory.create(/*realm,*/consumer, accessToken);
			request.readQueryStringAndAddToSignatureBaseString(url);
			request.setHeader("Content-Type", "application/json");

			String oAuthNonce = String.valueOf((new SecureRandom()).nextLong());
			Long oAuthTimestamp = System.currentTimeMillis() / 1000L;
			String signature = request.getSignature(url,
			                   HttpMethod.POST, oAuthNonce, oAuthTimestamp);

			String authorizationHeader = request
			             .getAuthorizationHeader(signature, oAuthNonce, oAuthTimestamp);

			return authorizationHeader;
		}
		
		public String getValue(String key) throws IOException {
			Properties prop = new Properties();
			String path = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\RestData.properties";
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			String value = prop.getProperty(key);
			return value;
		}
		
		public String getJsonPath(Response response, String key) {
			String responseST = response.asString();
			JsonPath js = new JsonPath(responseST);
			return js.get(key).toString();
		}
	
}
