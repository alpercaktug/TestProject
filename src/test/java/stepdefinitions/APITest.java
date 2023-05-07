package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class APITest {
    private String endpoint;
    private Response response;
    private String boardId;
    private String listId;
    private List<String> cardIds = new ArrayList<>();

    private final String key = "047c6c875cf64320a876f6333ce776c1";
    private final String token = "ATTAfc7d5c72c4c0bb7da72ee2e0420712dcb1684705b1e1553f2c984d8eb783a3ebBC2E09E0";

    @When("Create a board")
    public void createABoard() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("name", "DELETE BOARD TEST Windows")
                .queryParam("key", key)
                .queryParam("token", token)
                .when().log().all()
                .post("https://api.trello.com/1/boards/")
                .then().log().all()
                .extract().response();

        this.boardId = response.jsonPath().getString("id");
        System.out.println("----BOARD ID----" + boardId);

    }

    @And("Get a list from created board")
    public void getAListFromCreatedBoard() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .when().log().all()
                .get("https://api.trello.com/1/boards/" + boardId + "/lists")
                .then().log().all()
                .extract().response();

        this.listId = response.jsonPath().getString("[0].id");
        System.out.println("----LIST ID----" + listId);
    }

    @And("Create two cards on created board")
    public void createTwoCardsOnCreatedBoard() {
        for (int i = 0; i<2; i++){
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .queryParam("idList", listId)
                    .queryParam("key", key)
                    .queryParam("token", token)
                    .when().log().all()
                    .post("https://api.trello.com/1/cards")
                    .then().log().all()
                    .extract().response();

            cardIds.add(response.jsonPath().getString("id"));
        }
        System.out.println(cardIds);
    }


    @Then("Update one of them randomly")
    public void updateOneOfThemRandomly() {
        Random random = new Random();
        int i = random.nextInt(cardIds.size());
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "updated")
                .when().log().all()
                .put("https://api.trello.com/1/cards/" + cardIds.get(i) )
                .then().log().all()
                .extract().response();
    }

    @Then("Delete Cards")
    public void deleteCards() {
        for (int i = 0; i<2; i++){
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .queryParam("key", "047c6c875cf64320a876f6333ce776c1")
                    .queryParam("token", "ATTAfc7d5c72c4c0bb7da72ee2e0420712dcb1684705b1e1553f2c984d8eb783a3ebBC2E09E0")
                    .when().log().all()
                    .delete("https://api.trello.com/1/cards/" + cardIds.get(i) )
                    .then().log().all()
                    .extract().response();
        }
    }

    @And("Delete Board")
    public void deleteBoard() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .when().log().all()
                .delete("https://api.trello.com/1/boards/" + boardId)
                .then().log().all()
                .extract().response();

    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String expectedValue) {
        response.then().assertThat().body("body", CoreMatchers.containsString(expectedValue));
    }

}
