package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks{
	
	@Before("@DeletePlace")
	public void beforeScenrio() throws IOException
	{
	
		//Write a code that will give you placeId
		stepDefinition m = new stepDefinition();
		//Call method only when placeId is null
		//static variable should be called using class name only
		if(stepDefinition.place_id==null)
		{
		m.add_place_payload_with("Saran", "lang1", "India");
		m.user_calls_with_http_request("AddPlaceAPI","POST");
		m.verify_place_id_created_maps_to_using("Saran", "getPlaceAPI");
		}
		
	}
}
