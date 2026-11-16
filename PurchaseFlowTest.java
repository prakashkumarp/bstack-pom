package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

public class PurchaseFlowTest extends BaseTest {

    private static final String USER = "demouser";
    private static final String PASS = "testingisfun99";

    private HomePage loginAsDemoUser() {
        driver.findElement(By.id("signin")).click();
        return new LoginPage(driver).login(USER, PASS);
    }

    @Test
    public void validLoginShowsUsername() {
        HomePage home = loginAsDemoUser();
        Assert.assertTrue(home.isLoaded(), "Product list should be visible");
        Assert.assertEquals(home.getLoggedInUser(), USER);
    }

    @Test
    public void invalidUserShowsError() {
        driver.findElement(By.id("signin")).click();
        LoginPage login = new LoginPage(driver)
                .loginExpectingFailure("locked_user", PASS);
        Assert.assertTrue(login.getErrorMessage().toLowerCase().contains("locked"));
    }

    @Test
    public void addProductToCart() {
        HomePage home = loginAsDemoUser();
        CartPage cart = home.addProductToCart(0);

        Assert.assertTrue(cart.isCartOpen());
        Assert.assertEquals(cart.getItemCount(), 1);
    }

    @Test
    public void endToEndPurchase() {
        HomePage home = loginAsDemoUser();
        CartPage cart = home.addProductToCart(0);
        Assert.assertEquals(cart.getItemCount(), 1);

        CheckoutPage checkout = cart.proceedToCheckout()
                .fillShippingDetails("John", "Doe", "123 Main Street", "Telangana", "500001")
                .submit();

        Assert.assertTrue(
                checkout.getConfirmationMessage().contains("Your Order has been successfully placed"));
    }
}