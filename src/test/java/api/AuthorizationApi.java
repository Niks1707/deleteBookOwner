package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import models.RegisterRequest;
import models.RegisterResponse;
import java.io.File;
import java.io.IOException;
import static specs.LogSpec.RequestSpec;
import static specs.LogSpec.ResponseSpec200;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {
    @Step("Авторизация пользователя")
    public static RegisterResponse getAuthCookie(){
        ObjectMapper objectMapper = new ObjectMapper();
        RegisterRequest registerRequest;
        File jsonFile = new File(AuthorizationApi.class.getResource("/loginData.json").getFile());
        try {
            registerRequest= objectMapper.readValue(jsonFile, RegisterRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return given(RequestSpec)
                .body(registerRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(ResponseSpec200)
                .extract().as(RegisterResponse.class);
    }
    
}
