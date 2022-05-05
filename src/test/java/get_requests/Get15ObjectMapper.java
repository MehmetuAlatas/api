package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {
                    /*
                    Given
                                https://restful-booker.herokuapp.com/booking/12  (==> updated 2 to 12 because 2 gives error)
                        When
                                I send GET Request to the URL
                        Then
                                Status code is 200
                {
                            "firstname": "James",
                            "lastname": "Brown",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"
                        }  */
//    @Test
//    public void get01(){
//        spec.pathParams("1","booking","2",2);
//        String expectedData = "{\n" +
//                "\"firstname\": \"Jim\",\n" +
//                "\"lastname\": \"Smith\",\n" +
//                "\"totalprice\": 649,\n" +
//                "\"depositpaid\": false,\n" +
//                "\"bookingdates\": {\n" +
//                "\"checkin\": \"2015-09-16\",\n" +
//                "\"checkout\": \"2018-04-09\"\n" +
//                "},\n" +
//                "\"additionalneeds\": \"Breakfast\"\n" +
//                "}";
//       // BookingPojo expectedDataPojo=JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);
//      //  Response response = given().spec(spec).when().get()
//
//    }
    @Test
    public void get02(){
        spec.pathParams("1","booking","2",12);

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");

        BookingPojo bookingPojo = new BookingPojo("James","Brown",111,true,bookingDatesPojo,"Breakfast");

        Response response = given().spec(spec).when().get("/{1}/{2]");

        BookingPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

        assertEquals(bookingPojo.getFirstname(),actualData.getFirstname());
        assertEquals(bookingPojo.getLastname(),actualData.getLastname());
        assertEquals(bookingPojo.getTotalprice(),actualData.getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingPojo.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());

    }
}
