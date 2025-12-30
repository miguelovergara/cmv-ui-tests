package cl.cmvlosrobles.qa.tests;

import cl.cmvlosrobles.qa.base.BaseTest;
import cl.cmvlosrobles.qa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ContentTests extends BaseTest {

    @Test(description = "Validate presence and content of Call button")
    public void testCallButton() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isCallButtonDisplayed(), "The Call (Phone) button is not displayed on the Home Page.");
        String phoneHref = homePage.getCallButtonHref();
        //Assert.assertTrue(phoneHref.equals("tel:+56712431284"), "The Call button does not have a valid 'tel:' link. Found: " + phoneHref);
        Assert.assertEquals(phoneHref,"tel:+56712431284", "The Call button does not have a valid link");
    }

    @Test(description = "Validate presence and content of WhatsApp button")
    public void testWhatsappButton() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isWhatsappButtonDisplayed(), "The WhatsApp button is not displayed on the Home Page.");
        String wsHref = homePage.getWhatsappButtonHref();
        //Assert.assertTrue(wsHref.equals("https://wa.me/56712431284"), "The WhatsApp button does not point to a valid WhatsApp link. Found: " + wsHref);
        Assert.assertEquals(wsHref,"https://wa.me/56712431284", "The WhatsApp button does not point to a valid WhatsApp link");
    }

    @Ignore
    @Test(description = "Verify Main Branding and Contact Info")
    public void testHomeBrandingAndContact() {
        HomePage homePage = new HomePage(driver);

        // Assert Title (Branding check)
        String titleText = homePage.getMainTitleText();
        Assert.assertTrue(titleText.toLowerCase().contains("los robles"),
                "Main title branding is incorrect or missing. Found: " + titleText);

        // Verify specific text content for contact via page source as a fallback
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("contacto@cmvlosrobles.cl"), "Contact email not found in page source.");
    }
}