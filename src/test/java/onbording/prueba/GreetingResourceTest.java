package onbording.prueba;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
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
                .statusCode(200)
                .body("$.size()", is(4),
                        "[0].nombre", is("televisor"),
                        "[0].stock", is(12.0F),
                        "[0].precio", is(1200.0F),
                        "[1].nombre", is("cargador"),
                        "[1].stock", is(1200.0F),
                        "[1].precio", is(30.0F),
                        "[2].nombre", is("telefono"),
                        "[2].stock", is(1000.0F),
                        "[2].precio", is(650.0F),
                        "[3].nombre", is("cargador"),
                        "[3].stock", is(1200.0F),
                        "[3].precio", is(30.0F));
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