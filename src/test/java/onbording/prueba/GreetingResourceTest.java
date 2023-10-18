package onbording.prueba;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GreetingResourceTest {

    @Test
    @Order(1)
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    @Order(2)
    public void testPostProduct(){
        given()
                .body("{\"nombre\": \"televisor\", \"stock\": \"12\", \"precio\": \"1200\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("/products")
                .then()
                .statusCode(201);

        given()
                .body("{\"nombre\": \"computador\", \"stock\": \"50\", \"precio\": \"12000\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("/products")
                .then()
                .statusCode(201);


        given()
                .body("{\"nombre\": \"telefono\", \"stock\": \"1000\", \"precio\": \"650\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("/products")
                .then()
                .statusCode(201);

        given()
                .body("{\"nombre\": \"cargador\", \"stock\": \"1200\", \"precio\": \"30\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("/products")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(3)
    public void testFindByIdProduct() throws NullPointerException  {
        given()
                .when()
                .header("Content-Type", "application/json")
                .body("{\"nombre\": \"cargador\", \"stock\": \"1200\", \"precio\": \"30\"}")
                .pathParam("id", 2)
                .put("/products/{id}", 2)
                .then().statusCode(204);

    }

    @Test
    @Order(4)
    public void TestAllProducts(){
        given()
                .when().get("/products")
                .then()
                .statusCode(200);
    }


    @Test
    @Order(5)
    public void testDeleteMethod(){
        given()
                .when()
                .header("Content-Type", "application/json")
                .pathParam("id", 3)
                .delete("/products/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(6)
    public void testFindByIdProduct404() {
        given()
                .when()
                .header("Content-Type", "application/json")
                .body("{\"nombre\": \"cargador\", \"stock\": \"1200\", \"precio\": \"30\"}")
                .pathParam("id", 8)
                .put("/products/{id}", 2)
                .then().statusCode(404);

    }

    @Test
    @Order(7)
    public void testDeleteMethodNotfpund(){
        given()
                .when()
                .header("Content-Type", "application/json")
                .pathParam("id", 12)
                .delete("/products/{id}")
                .then()
                .statusCode(404);
    }

}