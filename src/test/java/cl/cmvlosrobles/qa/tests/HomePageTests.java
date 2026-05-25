package cl.cmvlosrobles.qa.tests;

import cl.cmvlosrobles.qa.base.BaseTest;
import cl.cmvlosrobles.qa.pages.HomePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Feature("Home Page")
public class HomePageTests extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(driver);
    }

    // =========================================================
    // TOP HEADER
    // =========================================================

    @Story("Top Header")
    @Test(description = "Top header phone number is visible and links to WhatsApp")
    public void testTopHeaderPhone() {
        Assert.assertTrue(homePage.isTopHeaderPhoneDisplayed(), "Top header phone not displayed");
        Assert.assertEquals(homePage.getTopHeaderPhoneText(), "(+56) 71 243 1284",
                "Top header phone text mismatch");
        Assert.assertEquals(homePage.getTopHeaderPhoneHref(), "https://wa.me/56712431284",
                "Top header phone href mismatch");
    }

    @Story("Top Header")
    @Test(description = "Top header email is visible and links to mailto")
    public void testTopHeaderEmail() {
        Assert.assertTrue(homePage.isTopHeaderEmailDisplayed(), "Top header email not displayed");
        Assert.assertEquals(homePage.getTopHeaderEmailText(), "contacto@cmvlosrobles.cl",
                "Top header email text mismatch");
        Assert.assertEquals(homePage.getTopHeaderEmailHref(), "mailto:contacto@cmvlosrobles.cl",
                "Top header email href mismatch");
    }

    @Story("Top Header")
    @Test(description = "Top header Facebook icon is visible and links correctly")
    public void testTopHeaderFacebook() {
        Assert.assertTrue(homePage.isTopHeaderFacebookDisplayed(), "Facebook icon not displayed");
        Assert.assertTrue(homePage.getTopHeaderFacebookHref().contains("facebook.com/cmvlosrobles"),
                "Facebook href mismatch: " + homePage.getTopHeaderFacebookHref());
    }

    @Story("Top Header")
    @Test(description = "Top header Instagram icon is visible and links correctly")
    public void testTopHeaderInstagram() {
        Assert.assertTrue(homePage.isTopHeaderInstagramDisplayed(), "Instagram icon not displayed");
        Assert.assertTrue(homePage.getTopHeaderInstagramHref().contains("instagram.com/veterinarialosrobles"),
                "Instagram href mismatch: " + homePage.getTopHeaderInstagramHref());
    }

    // =========================================================
    // LOGO & SEARCH
    // =========================================================

    @Story("Logo")
    @Test(description = "Logo is visible with correct alt text and src")
    public void testLogo() {
        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo not displayed");
        Assert.assertEquals(homePage.getLogoAlt(), "Centro Médico Veterinario Los Robles",
                "Logo alt text mismatch");
        Assert.assertTrue(homePage.getLogoSrc().contains("logo"),
                "Logo src does not reference expected image: " + homePage.getLogoSrc());
    }

    @Story("Search")
    @Test(description = "Search icon is present in the header")
    public void testSearchIconVisible() {
        Assert.assertTrue(homePage.isSearchIconPresent(), "Search icon not present in header");
    }

    // =========================================================
    // MAIN MENU
    // =========================================================

    @Story("Main Menu")
    @Test(description = "Main menu contains all expected items in order")
    public void testMainMenuItems() {
        List<String> expected = Arrays.asList(
                "PORTAL CLIENTES", "INICIO", "HORARIO", "SOBRE NOSOTROS",
                "NUESTRO EQUIPO", "SERVICIOS", "UBICACIÓN"
        );
        List<String> actual = homePage.getMainMenuTexts();
        Assert.assertEquals(actual, expected, "Main menu items mismatch");
    }

    @Story("Main Menu")
    @Test(description = "Main menu links point to correct hrefs")
    public void testMainMenuLinks() {
        List<String> hrefs = homePage.getMainMenuHrefs();
        Assert.assertTrue(hrefs.get(0).contains("crmveterinario.com/inicio-cliente"),
                "Portal Clientes link mismatch");
        Assert.assertTrue(hrefs.get(1).contains("#inicio"),   "Inicio link mismatch");
        Assert.assertTrue(hrefs.get(2).contains("#horario"),  "Horario link mismatch");
        Assert.assertTrue(hrefs.get(3).contains("nosotros"),  "Sobre Nosotros link mismatch");
        Assert.assertTrue(hrefs.get(4).contains("nuestro-equipo"), "Nuestro Equipo link mismatch");
        Assert.assertTrue(hrefs.get(5).contains("#services"), "Servicios link mismatch");
        Assert.assertTrue(hrefs.get(6).contains("#ubicacion"),"Ubicación link mismatch");
    }

    // =========================================================
    // HERO SECTION
    // =========================================================

    @Story("Hero Section")
    @Test(description = "Hero title is displayed with correct branding text")
    public void testHeroTitle() {
        Assert.assertTrue(homePage.isHeroTitleDisplayed(), "Hero title not displayed");
        Assert.assertEquals(homePage.getHeroTitleText(), "Centro Médico Veterinario Los Robles",
                "Hero title text mismatch");
    }

    @Story("Hero Section")
    @Test(description = "Hero subtitle is displayed with correct description")
    public void testHeroSubtitle() {
        Assert.assertTrue(homePage.isHeroSubtitleDisplayed(), "Hero subtitle not displayed");
        String subtitle = homePage.getHeroSubtitleText();
        Assert.assertTrue(subtitle.contains("servicios de atención veterinaria"),
                "Hero subtitle missing expected content. Found: " + subtitle);
        Assert.assertTrue(subtitle.contains("región del Maule"),
                "Hero subtitle missing region. Found: " + subtitle);
    }

    @Story("Hero Section")
    @Test(description = "Reservation button is displayed and links to WhatsApp")
    public void testHeroReservationButton() {
        Assert.assertTrue(homePage.isCallButtonDisplayed(), "Reservation button not displayed");
        Assert.assertEquals(homePage.getCallButtonHref(), "https://wa.me/56712431284",
                "Reservation button href mismatch");
    }

    // =========================================================
    // HORARIO SECTION
    // =========================================================

    @Story("Horario Section")
    @Test(description = "Horario section heading is displayed")
    public void testHorarioHeading() {
        Assert.assertTrue(homePage.isHorarioSectionDisplayed(), "Horario section not displayed");
        Assert.assertEquals(homePage.getHorarioHeadingText(), "Nuestro Horario",
                "Horario heading text mismatch");
    }

    @Story("Horario Section")
    @Test(description = "Horario weekday schedule text is correct")
    public void testHorarioWeekdays() {
        Assert.assertEquals(homePage.getHorarioWeekdaysText(),
                "Lunes a Viernes: 10 a 13 Hrs y 15 a 19 Hrs",
                "Weekday schedule text mismatch");
    }

    @Story("Horario Section")
    @Test(description = "Horario weekend schedule text is correct")
    public void testHorarioWeekend() {
        Assert.assertEquals(homePage.getHorarioWeekendText(),
                "Sábado, Domingo y Feriado: Cerrado",
                "Weekend schedule text mismatch");
    }

    // =========================================================
    // NOSOTROS SECTION
    // =========================================================

    @Story("Nosotros Section")
    @Test(description = "Misión card is displayed in the Nosotros section")
    public void testNosotrosMision() {
        Assert.assertTrue(homePage.isMisionHeadingDisplayed(), "Misión heading not displayed");
        Assert.assertEquals(homePage.getMisionHeadingText(), "Misión",
                "Misión heading text mismatch");
    }

    @Story("Nosotros Section")
    @Test(description = "Visión card is displayed in the Nosotros section")
    public void testNosotrosVision() {
        Assert.assertTrue(homePage.isVisionHeadingDisplayed(), "Visión heading not displayed");
        Assert.assertEquals(homePage.getVisionHeadingText(), "Visión",
                "Visión heading text mismatch");
    }

    // =========================================================
    // QUOTE SECTION
    // =========================================================

    @Story("Quote Section")
    @Test(description = "Gandhi quote is displayed with correct text")
    public void testGandhiQuote() {
        Assert.assertTrue(homePage.isGandhiQuoteDisplayed(), "Gandhi quote not displayed");
        Assert.assertTrue(homePage.getGandhiQuoteText().contains("Mahatma Gandhi"),
                "Gandhi quote author missing");
        Assert.assertTrue(homePage.getGandhiQuoteText().contains("progreso moral"),
                "Gandhi quote body text mismatch");
    }

    // =========================================================
    // PACIENTES SECTION
    // =========================================================

    @Story("Pacientes Section")
    @Test(description = "Pacientes section heading is displayed")
    public void testPacientesHeading() {
        Assert.assertTrue(homePage.isPacientesHeadingDisplayed(), "Pacientes heading not displayed");
        Assert.assertTrue(homePage.getPacientesHeadingText().contains("nuestros pacientes"),
                "Pacientes heading text mismatch");
    }

    @Story("Pacientes Section")
    @Test(description = "Pacientes section invite text is displayed")
    public void testPacientesInvite() {
        Assert.assertTrue(homePage.getPacientesInviteText().contains("Envíanos la foto de tu cachorro"),
                "Pacientes invite text mismatch");
    }

    // =========================================================
    // SERVICES SECTION
    // =========================================================

    @Story("Services Section")
    @Test(description = "All 17 services are listed in the services section")
    public void testServicesComplete() {
        List<String> expected = Arrays.asList(
                "Medicina General", "Medicina Interna", "Animales Exóticos",
                "Oftalmología", "Gastroenterología", "Manejo del Dolor",
                "Dermatología", "Cirugía", "Exámenes Imagenológicos",
                "Exámenes de Laboratorio", "Medicina felina", "Cardiología",
                "Odontología", "Neurología", "Endocrinología",
                "Nefrourología", "Oncología"
        );
        List<String> actual = homePage.getServiceNames();
        Assert.assertEquals(actual.size(), expected.size(),
                "Expected " + expected.size() + " services but found " + actual.size() + ": " + actual);
        for (String service : expected) {
            Assert.assertTrue(actual.contains(service), "Service missing from list: " + service);
        }
    }

    // =========================================================
    // UBICACION SECTION
    // =========================================================

    @Story("Ubicacion Section")
    @Test(description = "Ubicacion section heading is displayed")
    public void testUbicacionHeading() {
        Assert.assertTrue(homePage.isUbicacionSectionDisplayed(), "Ubicacion section not displayed");
        Assert.assertEquals(homePage.getUbicacionHeadingText(), "¿Dónde nos encuentras?",
                "Ubicacion heading text mismatch");
    }

    @Story("Ubicacion Section")
    @Test(description = "Ubicacion address text and Google Maps link are correct")
    public void testUbicacionAddress() {
        Assert.assertTrue(homePage.isUbicacionAddressDisplayed(), "Address link not displayed");
        Assert.assertEquals(homePage.getUbicacionAddressText(),
                "24 Norte 4240, Parque San Valentín, Talca",
                "Address text mismatch");
        Assert.assertTrue(homePage.getUbicacionAddressHref().contains("maps"),
                "Address href does not point to maps");
    }

    @Story("Ubicacion Section")
    @Test(description = "Ubicacion phone links to WhatsApp")
    public void testUbicacionPhone() {
        Assert.assertEquals(homePage.getUbicacionPhoneHref(), "https://wa.me/56712431284",
                "Ubicacion phone href mismatch");
    }

    @Story("Ubicacion Section")
    @Test(description = "Ubicacion email links to mailto")
    public void testUbicacionEmail() {
        Assert.assertEquals(homePage.getUbicacionEmailHref(), "mailto:contacto@cmvlosrobles.cl",
                "Ubicacion email href mismatch");
    }
}
