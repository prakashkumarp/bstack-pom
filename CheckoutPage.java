package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By firstName    = By.id("firstNameInput");
    private final By lastName     = By.id("lastNameInput");
    private final By address      = By.id("addressLine1Input");
    private final By state        = By.id("provinceInput");
    private final By postalCode   = By.id("postCodeInput");
    private final By submitBtn    = By.id("checkout-shipping-continue");
    private final By confirmation = By.id("confirmation-message");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage fillShippingDetails(String first, String last, String addr,
                                            String stateName, String zip) {
        type(firstName, first);
        type(lastName, last);
        type(address, addr);
        type(state, stateName);
        type(postalCode, zip);
        return this;
    }

    public CheckoutPage submit() {
        click(submitBtn);
        return this;
    }

    public String getConfirmationMessage() {
        return getText(confirmation);
    }
}