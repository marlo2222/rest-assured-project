package teste;

import org.junit.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class UserXMLTest {

    @Test
    public void devoTrabalharComXML(){
        given()
                .when()
                    .get("https://restapi.wcaquino.me/usersXML/3")
                .then()
                    .statusCode(200)
                    .body("user.name", is("Ana Julia"))
                    .body("user.@id", is("3"))
                    .body("user.filhos.name.size()", is(2))
                    .body("user.filhos.name", hasItem("Luizinho"));
    }

    @Test
    public void devoPesquisasAvancadasComXML(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/usersXML/3")
                .then()
                .statusCode(200)
                .body("users.user.size()", is(3))
                .body("users.user.findAll{it.age.toInterger <= 25}.size()", is(2))
                .body("users.user.@id", hasItems("1", "2", "3"))
                .body("users.user.find{it.age == 25}.name", is("Maria Joaquina"))
                .body("users.user.findAll{it.name.contains('n')}.name", hasItems("Maria Joaquina", "Ana Julia"))
                .body("users.user.name.findAll{it.toString().startsWith('Maria')}.collect{it.toString().toUpperCase()}", is("MARIA JOAQUINA"));
    }
}
