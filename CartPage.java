package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartPanel     = By.cssSelector(".float-cart--open");
    private final By cartItems     = By.cssSelector(".float-cart__shelf-container .shelf-item");
    private final By cartItemTitle = By.cssSelector(".float-cart__shelf-container .title");
    private final By subTotal      = By.cssSelector(".sub-price__val");
    private final By checkoutBtn   = By.cssSelector(".buy-btn");
    private final By closeBtn      = By.cssSelector(".float-cart__close-btn");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartOpen() {
        return isDisplayed(cartPanel);
    }

    public int getItemCount() {
        return waitAllVisible(cartItems).size();
    }

    public List<WebElement> getItemTitles() {
        return waitAllVisible(cartItemTitle);
    }

    public String getSubTotal() {
        return getText(subTotal);
    }

    public CheckoutPage proceedToCheckout() {
        click(checkoutBtn);
        return new CheckoutPage(driver);
    }

    public HomePage closeCart() {
        click(closeBtn);
        return new HomePage(driver);
    }
}