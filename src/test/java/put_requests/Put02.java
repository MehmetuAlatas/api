package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.ResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Put02 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */
    /*
        Given
            https://dummy.restapiexample.com/api/v1/update/21
        When
            Sent PUT request
        Then
            Status code is 200
        And
            response body is
            {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
              }

     */
    @Test
    public void put01(){
        spec.pathParams("first","update","second",21);

        DummyApiDataPojo requestDataPojo = new DummyApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        ResponseBodyPojo expectedDataPojo =new ResponseBodyPojo("success",requestDataPojo,"Successfully! Record has been updated.");

        Response response = given().
                spec(spec).
                contentType(ContentType.JSON).
                body(requestDataPojo).
                when().
                put("/{first}/{second}");

        ResponseBodyPojo actualResponsePojo =
                JsonUtil.convertJsonToJavaObject(response.asString(),ResponseBodyPojo.class);

        //System.out.println(actualResponsePojo);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedDataPojo.getStatus(),actualResponsePojo.getStatus());
        assertEquals(expectedDataPojo.getData().getEmployee_name(),
                actualResponsePojo.getData().getEmployee_name());
        assertEquals(expectedDataPojo.getData().getEmployee_salary(),
                actualResponsePojo.getData().getEmployee_salary());
        assertEquals(expectedDataPojo.getData().getEmployee_age(),
                actualResponsePojo.getData().getEmployee_age());
        assertEquals(expectedDataPojo.getData().getProfile_image(),
                actualResponsePojo.getData().getProfile_image());
        assertEquals(expectedDataPojo.getMessage(),actualResponsePojo.getMessage());

    }
}