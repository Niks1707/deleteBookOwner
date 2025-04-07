package api;

import io.qameta.allure.Step;
import models.AddBookRequest;
import models.AddBookResponse;
import static io.restassured.RestAssured.given;
import static specs.LogSpec.*;

public class BookShopApi {
    @Step("Запрос на добавление книги в корзину")
    public static AddBookResponse addBook(AddBookRequest addBookRequest , String token) {
        return given(RequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(addBookRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(ResponseSpec201)
                .extract().as(AddBookResponse.class);
    }
    @Step("Запрос на удаление книги из корзины")
    public static String deleteAllBooks (String token, String userId){
        return given(RequestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(ResponseSpec204).toString();
    }
}
