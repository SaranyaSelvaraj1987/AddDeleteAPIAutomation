package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import Resources.APIResources;

public class stepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 res=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}
	@When("user calls {string} with {string} HTTP request")
		public void user_calls_with_http_request(String resource, String method) {
		    // Write code here that turns the phrase above into concrete actions
			//Contsructor will be called with value of resources
			APIResources resourceAPI = APIResources.valueOf(resource);
			System.out.println("APIResources API"+resourceAPI.getResources());
			resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		    if(method.equalsIgnoreCase("POST"))
			response =res.when().post(resourceAPI.getResources());
		    else if(method.equalsIgnoreCase("GET"))
		    response =res.when().get(resourceAPI.getResources());
		   // .then().spec(resspec).extract().response();
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		int actual = response.getStatusCode(); //JSONParse in Utils file
		assertEquals(actual,200);
	}
	@Then("the {string} response body is {string}")
	public void the_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		//toString - to convert keyValue into String
		assertEquals(getJsonPath(response,keyValue).toString(),Expectedvalue);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response,"place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		//using the existing step definition below
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}
	@Given("DeletePlace Payload")
	public void deletePlace_Payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}

}
