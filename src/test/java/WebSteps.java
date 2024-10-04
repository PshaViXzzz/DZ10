import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }
    @Step("ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("кликаем по ссылке в репозитории {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("открываем таб issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("проверяем наличие issue {issue}")
    public void shouldSeeIssueWithNumber(int issue) {
        $(byText("#" + issue + " opened")).should(Condition.exist);
    }
}
