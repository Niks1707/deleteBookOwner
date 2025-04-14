package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import models.RegisterRequest;
import models.RegisterResponse;
import java.io.File;
import java.io.IOException;
import static specs.LogSpec.requestSpec;
import static specs.LogSpec.responseSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApiSteps {
    @Step("Авторизация пользователя")
    public static RegisterResponse getAuthCookie() {
        ObjectMapper objectMapper = new ObjectMapper();
        RegisterRequest registerRequest;
        File jsonFile = new File("src/test/resources/loginData.json");
        try {
            registerRequest = objectMapper.readValue(jsonFile, RegisterRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return given(requestSpec)
                .body(registerRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec(200))
                .extract().as(RegisterResponse.class);
    }

}
