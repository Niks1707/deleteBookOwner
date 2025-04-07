package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
     private SelenideElement deleteAllButton = $(".text-right.button.di").$("#submit");
    private SelenideElement deleteAllButtonConfirmPanel = $(".modal-content");
    private SelenideElement confirmDeleteAllButton = $("#closeSmallModal-ok");

    @Step("Открываем страницу корзины")
    public ProfilePage openLoginPage() {
        open("/profile");
        Selenide.executeJavaScript("$('fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
        return this;
    }
    @Step("Нажатие на удаление всех книг из корзины")
    public ProfilePage clickDeleteAllBooksButton() {
        deleteAllButton.click();
        return this;
    }
    @Step("Подтверждение удаления книг в окне")
    public ProfilePage confirmDeleteAllBooks() {
        confirmDeleteAllButton.click();
        return this;
    }
    @Step("Проверка, что книг нет в корзине")
    public boolean isBookRemovedSuccessful() {
        deleteAllButtonConfirmPanel.shouldBe(Condition.hidden);
        boolean pageNotHaveBooks = $("body").has(Condition.text("No rows found"));
        return pageNotHaveBooks;
    }
}
