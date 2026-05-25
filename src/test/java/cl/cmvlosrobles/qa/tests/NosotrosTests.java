package cl.cmvlosrobles.qa.tests;

import cl.cmvlosrobles.qa.base.BaseTest;
import cl.cmvlosrobles.qa.pages.NosotrosPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Feature("Nosotros")
public class NosotrosTests extends BaseTest {

    private static final String NOSOTROS_URL = "https://cmvlosrobles.cl/nosotros/";

    private NosotrosPage page;

    @BeforeMethod
    public void goToNosotros() {
        driver.get(NOSOTROS_URL);
        page = new NosotrosPage(driver);
    }

    // =========================================================
    // PAGE IDENTITY
    // =========================================================

    @Story("Page Identity")
    @Test(description = "Page title contains clinic and section name")
    public void testPageTitle() {
        Assert.assertTrue(page.getPageTitle().contains("Los Robles"),
                "Page title missing clinic name. Found: " + page.getPageTitle());
        Assert.assertTrue(page.getPageTitle().toLowerCase().contains("nosotros"),
                "Page title missing 'nosotros'. Found: " + page.getPageTitle());
    }

    @Story("Page Identity")
    @Test(description = "Page H1 heading reads '¿Quiénes Somos?'")
    public void testPageHeading() {
        Assert.assertTrue(page.isHeadingDisplayed(), "Page heading not displayed");
        Assert.assertEquals(page.getHeadingText(), "¿Quiénes Somos?",
                "Page heading text mismatch");
    }

    // =========================================================
    // HISTORY / QUIENES SOMOS CONTENT
    // =========================================================

    @Story("Quiénes Somos")
    @Test(description = "Origin paragraph mentions founding conviction and clinic origin")
    public void testOriginParagraph() {
        String text = page.getOriginText();
        Assert.assertTrue(text.contains("convicción"),
                "Origin paragraph missing 'convicción'. Found: " + text);
        Assert.assertTrue(text.contains("clínica"),
                "Origin paragraph missing 'clínica'. Found: " + text);
    }

    @Story("Quiénes Somos")
    @Test(description = "Founding paragraph mentions 2017 founding and Talca")
    public void testFoundingParagraph() {
        String text = page.getFoundingText();
        Assert.assertTrue(text.contains("2017"),
                "Founding paragraph missing year 2017. Found: " + text);
        Assert.assertTrue(text.contains("Talca"),
                "Founding paragraph missing 'Talca'. Found: " + text);
    }

    @Story("Quiénes Somos")
    @Test(description = "Approach paragraph mentions animal welfare and clinical decisions")
    public void testApproachParagraph() {
        String text = page.getApproachText();
        Assert.assertTrue(text.contains("bienestar animal"),
                "Approach paragraph missing 'bienestar animal'. Found: " + text);
        Assert.assertTrue(text.contains("decisiones clínicas"),
                "Approach paragraph missing 'decisiones clínicas'. Found: " + text);
    }

    @Story("Quiénes Somos")
    @Test(description = "Reach paragraph mentions Región del Maule coverage")
    public void testReachParagraph() {
        String text = page.getReachText();
        Assert.assertTrue(text.contains("Región del Maule"),
                "Reach paragraph missing 'Región del Maule'. Found: " + text);
    }

    @Story("Quiénes Somos")
    @Test(description = "Location paragraph mentions 2025 move to Parque San Valentín")
    public void testLocationParagraph() {
        String text = page.getLocationText();
        Assert.assertTrue(text.contains("2025"),
                "Location paragraph missing '2025'. Found: " + text);
        Assert.assertTrue(text.contains("Parque San Valentín"),
                "Location paragraph missing 'Parque San Valentín'. Found: " + text);
    }

    // =========================================================
    // MISIÓN / VISIÓN CONTENT
    // =========================================================

    @Story("Misión y Visión")
    @Test(description = "Misión paragraph describes delivering animal health solutions")
    public void testMisionParagraph() {
        String text = page.getMisionText();
        Assert.assertTrue(text.contains("misión"),
                "Misión paragraph missing keyword 'misión'. Found: " + text);
        Assert.assertTrue(text.contains("salud animal"),
                "Misión paragraph missing 'salud animal'. Found: " + text);
        Assert.assertTrue(text.contains("excelencia"),
                "Misión paragraph missing 'excelencia'. Found: " + text);
    }

    @Story("Misión y Visión")
    @Test(description = "Visión paragraph describes being the first choice for families")
    public void testVisionParagraph() {
        String text = page.getVisionText();
        Assert.assertTrue(text.contains("primera opción"),
                "Visión paragraph missing 'primera opción'. Found: " + text);
        Assert.assertTrue(text.contains("confianza"),
                "Visión paragraph missing 'confianza'. Found: " + text);
    }

    // =========================================================
    // IMAGES
    // =========================================================

    @Story("Images")
    @Test(description = "Old clinic photo is displayed with correct alt text and loaded")
    public void testClinicOldPhoto() {
        Assert.assertTrue(page.isClinicOldPhotoDisplayed(), "Old clinic photo not displayed");
        Assert.assertTrue(page.isClinicOldPhotoLoaded(), "Old clinic photo not loaded (naturalWidth=0)");
        Assert.assertEquals(page.getClinicOldPhotoAlt(), "frente casa antigua",
                "Old clinic photo alt text mismatch");
    }

    @Story("Images")
    @Test(description = "New clinic photo is displayed with correct alt text and loaded")
    public void testClinicNewPhoto() {
        Assert.assertTrue(page.isClinicNewPhotoDisplayed(), "New clinic photo not displayed");
        Assert.assertTrue(page.isClinicNewPhotoLoaded(), "New clinic photo not loaded (naturalWidth=0)");
        Assert.assertEquals(page.getClinicNewPhotoAlt(), "foto frente casa acceso portón",
                "New clinic photo alt text mismatch");
    }

    @Story("Images")
    @Test(description = "All content images are loaded with non-zero naturalWidth")
    public void testAllContentImagesLoaded() {
        List<Boolean> loaded = page.getAllContentImagesLoaded();
        Assert.assertFalse(loaded.isEmpty(), "No content images found on the page");
        for (int i = 0; i < loaded.size(); i++) {
            Assert.assertTrue(loaded.get(i), "Content image #" + (i + 1) + " failed to load");
        }
    }
}
