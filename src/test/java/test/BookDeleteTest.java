package test;

import pages.ProfilePage;
import api.BookShopApiSteps;
import helpers.DataStorage;
import helpers.WithLogin;
import io.qameta.allure.Owner;
import models.AddBookRequest;
import models.RegisterResponse;
import org.junit.jupiter.api.Test;
import java.util.List;
@Owner(value = "Genkel Veronika")
public class BookDeleteTest extends TestBase {
    ProfilePage profilePage = new ProfilePage();

    @WithLogin
    @Test
    void bookDeleteTest() {
        RegisterResponse loginData = DataStorage.registerResponse;
        String isbn = DataStorage.isbn;
        BookShopApiSteps.deleteAllBooks(loginData.getToken(), loginData.getUserId());
        AddBookRequest addBookRequest = new AddBookRequest(loginData.getUserId(), List.of(new AddBookRequest.Isbn(isbn)));
        addBookRequest.setUserId(loginData.getUserId());
        BookShopApiSteps.addBook(addBookRequest, loginData.getToken());
        profilePage.openLoginPage()
                .clickDeleteAllBooksButton()
                .confirmDeleteAllBooks()
                .isBookRemovedSuccessful();
    }
}
