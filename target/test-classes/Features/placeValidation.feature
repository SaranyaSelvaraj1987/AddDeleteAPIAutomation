Feature: Add Place API Validations Retry
  I want to use this template for my AddPlace API Validations file

@AddPlace @Regression
  Scenario: AddPlace
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" HTTP request
		Then the API call is success with status code 200
		And the "status" response body is "OK"
		And the "scope" response body is "APP"
		And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
		|name    |language     |address         |
		|Saran56 |Telugu56       |Bangalore56      |
		|Leela56 |Tamil56        |Coimbatore56     |
		
@DeletePlace @Regression
  Scenario: DeletePlace
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" HTTP request
		Then the API call is success with status code 200
		And the "status" response body is "OK"		