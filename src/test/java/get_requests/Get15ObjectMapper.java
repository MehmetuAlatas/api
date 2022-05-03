package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {
                    /*
                    Given
                                https://restful-booker.herokuapp.com/booking/2
                        When
                                I send GET Request to the URL
                        Then
                                Status code is 200
                {
                "firstname": "Mark",
                "lastname": "Ericsson",
                "totalprice": 726,
                "depositpaid": true,
                "bookingdates": {
                                    "checkin": "2015-08-07",
                                    "checkout": "2020-10-25"
                                 },
                "additionalneeds": "Breakfast"
                }
                     */
    @Test
    public void get01(){
        spec.pathParams("1","booking","2",2);
        String expectedData = "{\n" +
                "\"firstname\": \"Jim\",\n" +
                "\"lastname\": \"Smith\",\n" +
                "\"totalprice\": 649,\n" +
                "\"depositpaid\": false,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2015-09-16\",\n" +
                "\"checkout\": \"2018-04-09\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";
        BookingPojo expectedDataPojo=JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);
        Response response = given().spec(spec).when().get()
    }
}
