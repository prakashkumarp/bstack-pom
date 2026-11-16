package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameDropdown = By.id("username");
    private final By passwordDropdown = By.id("password");
    private final By loginButton      = By.id("login-btn");
    private final By errorMessage     = By.cssSelector("h3.api-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By option(String text) {
        return By.xpath("//div[text()='" + text + "']");
    }

    public HomePage login(String username, String password) {
        selectCredentials(username, password);
        click(loginButton);
        return new HomePage(driver);
    }

    public LoginPage loginExpectingFailure(String username, String password) {
        selectCredentials(username, password);
        click(loginButton);
        return this;
    }

    private void selectCredentials(String username, String password) {
        click(usernameDropdown);
        click(option(username));
        click(passwordDropdown);
        click(option(password));
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}