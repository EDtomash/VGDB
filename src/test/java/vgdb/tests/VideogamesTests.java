package vgdb.tests;

import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import vgdb.controllers.VideogameApi;
import vgdb.models.SuccessfulOperationModel;
import vgdb.models.VideogameModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class VideogamesTests {
    Faker faker;
    VideogameModel zeldaGame;
    VideogameModel marioGame;
    Integer zeldaGameId;

    @BeforeClass
    public void setup() {
        faker = new Faker();

        zeldaGame = VideogameModel.builder()
                .id(faker.number().positive())
                .name(faker.name().name())
                .releaseDate("2026-07-08")
                .reviewScore(faker.number().numberBetween(0, 10))
                .category("adventure")
                .rating("mature")
                .build();
        zeldaGameId = zeldaGame.getId();

        marioGame = VideogameModel.builder()
                .id(zeldaGameId)
                .name(faker.name().femaleFirstName())
                .releaseDate("2026-07-08")
                .reviewScore(faker.number().numberBetween(0, 10))
                .category("adventure")
                .rating("mature")
                .build();
    }

    @Test
    public void getAllVideogames() {
        Response response = VideogameApi.getVideogame();
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void addingVideogame() {

        Response response = VideogameApi.postVideogame(zeldaGame);
        SuccessfulOperationModel successfulOperationModel = response.as(SuccessfulOperationModel.class);

        assertThat(response.statusCode(), is(200));
        assertThat(successfulOperationModel.getStatus(), equalTo("Record Added Successfully"));
    }

    @Test
    public void deleteVideogameById() {
        Response response = VideogameApi.deleteVideogame(zeldaGame.getId());
        SuccessfulOperationModel successfulResponse = response.as(SuccessfulOperationModel.class);

        assertThat(response.statusCode(), is(200));
        assertThat(successfulResponse.getStatus(), equalTo("Record Deleted Successfully"));
    }

    @Test
    public void getVideogameById() {
        Response response = VideogameApi.getVigeogameById(1);

        assertThat(response.statusCode(), is(200));
        assertThat(response.path("id"), equalTo(1));
        assertThat(response.path("name"), equalTo("Resident Evil 4"));
    }

    @Test
    public void updatingExsistingVideogame() {
        Response response = VideogameApi.postVideogame(zeldaGame);

        SuccessfulOperationModel successMessage = response.as(SuccessfulOperationModel.class);
        assertThat(successMessage.getStatus(), equalTo("Record Added Successfully"));
        assertThat(response.statusCode(), is(200));

        Response updatedResponse = VideogameApi.updateVideogame(marioGame, zeldaGameId);

        assertThat(updatedResponse.statusCode(), equalTo(200));
        assertThat(updatedResponse.path("id"), equalTo(zeldaGameId));
        assertThat(updatedResponse.path("name"), equalTo(marioGame.getName()));
    }
}
