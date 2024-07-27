package vgdb.controllers;

import io.restassured.response.Response;
import vgdb.models.VideogameModel;

import static io.restassured.RestAssured.given;

public class VideogameApi extends SpecBuilder {

    public static Response getVideogame() {
        return given(getRequestSpec())
                .when()
                .get("/videogames")
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response getVigeogameById(Integer videogameId) {
        return given(getRequestSpec())
                .when()
                .get("/videogames/" + videogameId)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response postVideogame(VideogameModel videogame) {
        return given(getRequestSpec())
                .body(videogame)
                .when()
                .post("/videogames")
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response deleteVideogame(Integer videogameId) {
        return given(getRequestSpec())
                .when()
                .delete("/videogames/" + videogameId)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response updateVideogame(VideogameModel videogame, Integer videogameId) {
        return given(getRequestSpec())
                .body(videogame)
                .when()
                .put("/videogames/" + videogameId)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

}
