package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Review extends JsonPlaceHolderBaseUrl {
    /*
           Given
               https://restful-booker.herokuapp.com/booking/3
           When
               User sends a GET Request to the url
           Then
               HTTP Status Code should be 200
           And
               Content Type should be JSON
           And
               Status Line should be HTTP/1.1 200 OK
        */
    String url = "https://restful-booker.herokuapp.com/booking/3";

    @Test
    public void get01() {
        Response response = given().when().get(url);
        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");
    }

    @Test
    public void get02() {
               /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */
        String url = "https://restful-booker.herokuapp.com/booking/1";
        Response response = given().when().get(url);
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void get03() {
//        String url = "https://jsonplaceholder.typicode.com/todos/23";
//        Response response = given().when().get(url);
//        response.then().assertThat().statusCode(200).
//                contentType(ContentType.JSON).
//                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
//                        "completed",equalTo(false),
//                        "userId",equalTo(2));

        spec.pathParams("first","todos","second",23);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"));



    }
    @Test
    public void get04(){
        String url = "https://jsonplaceholder.typicode.com/todos";
        Response response=given().when().get(url);
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("id",hasSize(200),
                        "title",hasItem("quis eius est sint explicabo"),
                        "userId",hasItems(2,7,9));
    }
    @Test
    public void get05(){
        String url ="https://restful-booker.herokuapp.com/booking";
        Response response = given().when().get(url);
        response.then().assertThat().statusCode(200);



    }

}
