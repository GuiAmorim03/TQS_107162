package tqs.ua.pt;

import static io.restassured.RestAssured.*;
// import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class AppTest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void testListAllToDos() {
        given().
        when().
            get(BASE_URL+"/todos").
        then().
            statusCode(200);
    }

    @Test
    public void testToDo4() {
        given().
        when().
            get(BASE_URL+"/todos/4").
        then().
            statusCode(200).
            body("title", equalTo("et porro tempora"));
    }

    @Test
    public void testAllAndGet198And199(){
        given().
        when().
            get(BASE_URL+"/todos").
        then().
            statusCode(200).
            body("id", hasItems(198, 199));
    }

    @Test
    public void testAllAndGetLess2Seconds(){
        given().
        when().
            get(BASE_URL+"/todos").
        then().
            statusCode(200).
            time(lessThan(2000L)); // EM MILISEGUNDOS (2000L = 2 segundos)
    }
}
