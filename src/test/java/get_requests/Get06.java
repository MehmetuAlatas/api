package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get06 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/5
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
            {
                "firstname": "Mary",
                "lastname": "Jackson",
                "totalprice": 111,
                "depositpaid": false,
                "bookingdates": { "checkin": "2017-05-23",
                                  "checkout":"2019-07-02" }
            }
     */
    @Test
    public void get01(){
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        //1.Step: Set the Url
        spec.pathParams("first","booking", "second", 5);

        //2.Step: Set the Expected Data

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertions
        //1.Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Sally"),
                        "lastname", equalTo("Jackson"),
                        "totalprice", equalTo(949),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2016-09-04"),
                        "bookingdates.checkout", equalTo("2017-04-17"));

        //2.Way: We will use JsonPath Class
        JsonPath json = response.jsonPath();
        assertEquals("Sally", json.getString("firstname"));
        assertEquals("Jackson", json.getString("lastname"));
        assertEquals(949, json.getInt("totalprice"));
        assertEquals(false, json.getBoolean("depositpaid"));
        assertEquals("2016-09-04", json.getString("bookingdates.checkin"));
        assertEquals("2017-04-17", json.getString("bookingdates.checkout"));

        //3.Way: Use Soft Assertion

    }

}