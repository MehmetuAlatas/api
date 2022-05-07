package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.ResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Put03 extends DummyRestApiBaseUrl {
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
             URL: https://dummy.restapiexample.com/api/v1/update/21
        When
             user send PUT Request
             Request body: {
                                "employee_name": "Tom Hanks",
                                "employee_salary": 111111,
                                "employee_age": 23,
                                "profile_image": "Perfect image"
                             }
        Then
            Status code is 200
        And
            Response body should be
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
        spec.pathParams("1","update","2",21);

        DummyApiDataPojo expected =new DummyApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        ResponseBodyPojo expectedData = new ResponseBodyPojo("success",expected,"Successfully! Record has been updated.");

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expected).put("/{1}/{2}");
        response.prettyPrint();
        ResponseBodyPojo actual = JsonUtil.convertJsonToJavaObject(response.asString(),ResponseBodyPojo.class);
        assertEquals(expectedData.getStatus(),actual.getStatus());
        assertEquals(expectedData.getMessage(),actual.getMessage());
        assertEquals(expectedData.getData().getProfile_image(),actual.getData().getProfile_image());
        assertEquals(expectedData.getData().getEmployee_age(),actual.getData().getEmployee_age());
        assertEquals(expectedData.getData().getEmployee_salary(),actual.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_name(),actual.getData().getEmployee_name());




    }

}
