package put_requests;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
       Given
          1) https://jsonplaceholder.typicode.com/todos/198
          2) {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
              }
       When
        I send PUT Request to the Url
      Then
            Status code is 200
            And response body is like   {
                              "userId": 21,
                              "title": "Wash the dishes",
                              "completed": false
                             }
    */
    @Test
    public void put01(){
        //1.Step: Set the URL
        spec.pathParams("first","todos","second",198);
        //2.Step: Set the expected Data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap = expectedData.expectedDataWithAllKeys(21,"Wash the dishes",false);
        //3.Step: Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().put("/{first}/{second}");
        response.prettyPrint();
        //4.Step: Do Assertions
        Map<String,Object> actualDataMap = response.as(HashMap.class);
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
       /*
     {
               "name":"Polygon Sample",
               "geo_json":{
                  "type":"Feature",
                  "properties":{},
                  "geometry":{
                     "type":"Polygon",
                     "coordinates":[
                        [
                           [-121.1958,37.6683],
                           [-121.1779,37.6687],
                           [-121.1773,37.6792],
                           [-121.1958,37.6792],
                           [-121.1958,37.6683]
                        ]
                     ]
                  }
               }
            }
	When
		 I send POST Request to the Url
	Then
		Assert Status Code (201)
		And Response Body should be like {
										    "id": "5fd8c383714b523b2ce1f154",
										    "geo_json": {
										        "geometry": {
										            "coordinates": [
										                [
										                    [
										                        -121.1958,
										                        37.6683
										                    ],
										                    [
										                        -121.1779,
										                        37.6687
										                    ],
										                    [
										                        -121.1773,
										                        37.6792
										                    ],
										                    [
										                        -121.1958,
										                        37.6792
										                    ],
										                    [
										                        -121.1958,
										                        37.6683
										                    ]
										                ]
										            ],
										            "type": "Polygon"
										        },
										        "type": "Feature",
										        "properties": {
										        }
										    },
										    "name": "Polygon Sample",
										    "center": [
										        -121.1867,
										        37.67385
										    ],
										    "area": 190.9484,
										    "user_id": "5fd8c02a3da20c000759e0f8",
										    "created_at": 1608041347
										}
     */
    }
}