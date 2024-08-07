package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import net.serenitybdd.core.Serenity;

public class StepDefinition {
	
	RequestSpecification resq,resq2,resq3;
    
    
	Response res;
	@Given("set the base url")
	public void set_the_base_url() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		resq=RestAssured.given();
		Serenity.setSessionVariable("resqGlobal").to(resq);   //resqGlobal is globalvariable
	}
	@When("check the health of the api")
	public void check_the_health_of_the_api() {
	    // Write code here that turns the phrase above into concrete actions
		resq2=Serenity.sessionVariableCalled("resqGlobal");
		 res=resq2.get("/ping");
		 Serenity.setSessionVariable("resPingGlobal").to(res);
		 res.prettyPrint();
		 //resq2 is the local variable can be accessed within this method only
	   
	}	
	//in story file resping is 201 and resbooking is 200
	@Then("Validate the {string} to be {int}")
	public void validate_the_to_be(String resD, Integer int1) {// resD is taken from story file
		int expectedStatus=int1;
		
		//Response resActual=res.equals("resPing")?sessionVariableCalled("resGlobal");
		if(resD.equals("resPing")) {
			res=Serenity.sessionVariableCalled("resPingGlobal");
			
		}else if(resD.equals("resBooking")) {
			res=Serenity.sessionVariableCalled("resBookingGlobal");
		}
		   int actualStatus=res.getStatusCode();
		   System.out.println(res.statusCode());
		
		 Assert.assertEquals(expectedStatus, actualStatus);
		 
		
	}
	@When("to get the booking ids")
	public void to_get_the_booking_ids() {
	  resq3=Serenity.sessionVariableCalled("resqGlobal");
	  res=resq.get("/booking");
	  Serenity.setSessionVariable("resBookingGlobal").to(res);
	  res.prettyPrint();
	}
	
	
	

	


}
