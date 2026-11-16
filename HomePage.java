package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    private final By loggedInUser   = By.cssSelector(".username");
    private final By productCards   = By.cssSelector(".shelf-item");
    private final By productTitles  = By.cssSelector(".shelf-item__title");
    private final By addToCartBtn   = By.cssSelector(".shelf-item__buy-btn");
    private final By cartIcon       = By.cssSelector(".bag");
    private final By orderBySelect  = By.cssSelector(".sort select");
    private final By productCount   = By.cssSelector(".products-found span");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getLoggedInUser() {
        return getText(loggedInUser);
    }

    public boolean isLoaded() {
        return isDisplayed(productCards);
    }

    public int getProductCount() {
        return waitAllVisible(productCards).size();
    }

    public List<WebElement> getProductTitles() {
        return waitAllVisible(productTitles);
    }

    public HomePage filterByVendor(String vendor) {
        // Vendor filters are checkboxes whose clickable label wraps the vendor name
        click(By.xpath("//span[@class='checkmark' and ../input[@value='" + vendor + "']]"));
        return this;
    }

    public HomePage sortBy(String visibleText) {
        new org.openqa.selenium.support.ui.Select(waitVisible(orderBySelect))
                .selectByVisibleText(visibleText);
        return this;
    }

    public CartPage addProductToCart(int index) {
        List<WebElement> buttons = driver.findElements(addToCartBtn);
        buttons.get(index).click();   // cart panel opens automatically
        return new CartPage(driver);
    }

    public CartPage addProductToCartByName(String name) {
        By btn = By.xpath("//p[text()='" + name + "']/ancestor::div[contains(@class,'shelf-item')]"
                + "//div[contains(@class,'shelf-item__buy-btn')]");
        click(btn);
        return new CartPage(driver);
    }

    public CartPage openCart() {
        click(cartIcon);
        return new CartPage(driver);
    }
}