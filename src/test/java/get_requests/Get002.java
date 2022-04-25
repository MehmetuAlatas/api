package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get002 {
    @Test
    public void Get01(){
        // I think the problem is releated with language and api no worries
        String url = "https://restful-booker.herokuapp.com/booking/113";

        Response response = given().when().get(url);

        response.prettyPrint();

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
    }
}
