import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 90;
    @Test
    public void testLambdaIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("ищем репозиторий" + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("кликаем по ссылке в репозитории" + REPOSITORY, () -> {
                    $(linkText(REPOSITORY)).click();
                });
        step("открываем таб issues", () -> {
                    $("#issues-tab").click();
                });
        step("проверяем наличие issue" + ISSUE, () -> {
            $(byText("#" + ISSUE + " opened")).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }

}
