package delete_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDeleteHomework;
import pojos.ResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {

/*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
   HTTP Request Method: DELETE Request
   Test Case: Type by using Gherkin Language
   Assert:     i) Status code is 200
           ii) "status" is "success"
          iii) "data" is "2"
           iv) "message" is "Successfully! Record has been deleted"
 */
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/delete/2
    When
        I send DELETE Request
    Then
        Status code is 200
    And
        "status" is "success"
    And
        "data" is "2"
     And
        "message" is "Successfully! Record has been deleted"
     */
    @Test
    public void delete01(){
        //set the url
        spec.pathParams("1","delete","2",2);
        //set the expected data
        DummyDeleteHomework expected = new DummyDeleteHomework( "success", "2","Successfully! Record has been deleted");
        //send request and get response
        Response response = given().spec(spec).when().delete("/{1}/{2}");
        response.prettyPrint();

        DummyDeleteHomework actual = JsonUtil.convertJsonToJavaObject(response.asString(),DummyDeleteHomework.class);
                //do assertions
        response.then().statusCode(200);
        assertEquals(expected.getStatus(),actual.getStatus());
        assertEquals(expected.getData(),actual.getData());
        assertEquals(expected.getMessage(),actual.getMessage());


    }
}
