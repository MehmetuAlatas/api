package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.ResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
    /*
   URL: https://dummy.restapiexample.com/api/v1/employee/1
   HTTP Request Method: GET Request
   Test Case: Type by using Gherkin Language
   Assert:     i) Status code is 200
           ii) "employee_name" is "Tiger Nixon"
          iii) "employee_salary" is 320800
           iv)  "employee_age" is 61
            v) "status" is "success"
           vi)  "message" is "Successfully! Record has been fetched."
 */
    /*
    TEST CASE THAT WRİTTEN in GHERKIN LANGUAGE
 Given
    https://dummy.restapiexample.com/api/v1/employee/1
 When
    User sends the GET request and get the response
 Then
    Status code is 200
 And
    "employee_name" is "Tiger Nixon"
And
   "employee_salary" is 320800
And
   "employee_age" is 61
And
   "status" is "success"
And
   "message" is "Successfully! Record has been fetched."
  */
    @Test
    public void get01(){
        spec.pathParams("first","employee","second",1);

        DummyApiDataPojo dataPojo = new DummyApiDataPojo("Tiger Nixon",320800,61,"");
        ResponseBodyPojo responsePojo = new ResponseBodyPojo( "success",dataPojo, "Successfully! Record has been fetched.");

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        ResponseBodyPojo actual = JsonUtil.convertJsonToJavaObject(response.asString(),ResponseBodyPojo.class);

        assertEquals(responsePojo.getStatus(),actual.getStatus());
        assertEquals(responsePojo.getMessage(),actual.getMessage());
        assertEquals(responsePojo.getData().getEmployee_name(),actual.getData().getEmployee_name());
        assertEquals(responsePojo.getData().getEmployee_salary(),actual.getData().getEmployee_salary());
        assertEquals(responsePojo.getData().getEmployee_age(),actual.getData().getEmployee_age());
        assertEquals(responsePojo.getData().getProfile_image(),actual.getData().getProfile_image());
    }
}
