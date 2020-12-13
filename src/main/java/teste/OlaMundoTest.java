package teste;

import static io.restassured.RestAssured.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

public class OlaMundoTest {

    @Test
    public void testOlaMundo() {
        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo"));
        Assert.assertEquals(200, response.statusCode());

        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasRestAssured() {
        given()
                .when()
                    .get("https://restapi.wcaquino.me/ola")
                .then()//
                     .statusCode(200)
                     .body(is("Ola Mundo!"))
                     .body(containsString("Mundo"))
                     .body(is(not(nullValue())));
    }



}
