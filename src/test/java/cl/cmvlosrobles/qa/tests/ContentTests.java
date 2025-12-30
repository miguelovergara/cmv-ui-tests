package cl.cmvlosrobles.qa.tests;

import cl.cmvlosrobles.qa.base.BaseTest;
import cl.cmvlosrobles.qa.pages.HomePage;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class ContentTests extends BaseTest {

    @Test(description = "Validate presence of key navigation modules")
    public void testNavigationMenuContent() {
        HomePage homePage = new HomePage(driver);
        List<String> expectedItems = Arrays.asList(
            "INICIO", "PORTAL CLIENTES", "HORARIOS", "SOBRE NOSOTROS", "SERVICIOS", "UBICACIÓN Y CONTACTO"
        );
        
        List<String> actualItems = homePage.getMenuItemsLabels();
        
        for (String expected : expectedItems) {
            assertTrue(actualItems.stream().anyMatch(s -> s.equalsIgnoreCase(expected)),
                    "Menu item missing: " + expected);
        }
    }

    @Test(description = "Verify Main Branding and Contact Info")
    public void testHomeBrandingAndContact() {
        HomePage homePage = new HomePage(driver);
        
        // Assert Title (UX / SEO check)
        assertTrue(homePage.getMainTitleText().contains("Los Robles"),
                "Main title does not match branding.");

        // Check for specific footer/contact info
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("+56 71 243 1284"), "Phone number not found in content.");
        assertTrue(pageSource.contains("contacto@cmvlosrobles.cl"), "Contact email not found.");
    }

    @Test(description = "Validate Services Page redirection and content")
    public void testServicesContent() {
        HomePage homePage = new HomePage(driver);
        homePage.clickServices();
        
        assertTrue(driver.getCurrentUrl().contains("pservices") || driver.getCurrentUrl().contains("servicios"),
                "Navigation to services page failed.");
        
        // Validation for specific clinical services listed in the site
        List<String> expectedServices = Arrays.asList("Medicina general", "Imagenología", "Oftalmología");
        String bodyText = driver.getPageSource();
        
        for (String service : expectedServices) {
            assertTrue(bodyText.contains(service), "Service content missing: " + service);
        }
    }
}