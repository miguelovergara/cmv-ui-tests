package cl.cmvlosrobles.qa.tests;

import cl.cmvlosrobles.qa.base.BaseTest;
import cl.cmvlosrobles.qa.pages.NuestroEquipoPage;
import cl.cmvlosrobles.qa.pages.TeamMemberPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Feature("Nuestro Equipo")
public class NuestroEquipoTests extends BaseTest {

    private static final String EQUIPO_URL = "https://cmvlosrobles.cl/nuestro-equipo/";

    private static final Object[][] TEAM_MEMBERS = {
        {"Constanza Issotta Contardo", "Médica Veterinaria",   "https://cmvlosrobles.cl/constanza-issotta-contardo/"},
        {"Juan Ignacio Lara Luna",     "Médico Veterinario",   "https://cmvlosrobles.cl/juan-lara-luna/"},
        {"Camila Infante",             "Médica Veterinaria",   "https://cmvlosrobles.cl/camila-infante-basay/"},
        {"Jenifer Manriquez",          "Médica Veterinaria",   "https://cmvlosrobles.cl/jenifer-manriquez-poblete/"},
        {"Ana Bastías Bravo",          "Médica Veterinaria",   "https://cmvlosrobles.cl/ana-bastias-bravo/"},
        {"Andrea Nuñez Bustamante",    "Médica Veterinaria",   "https://cmvlosrobles.cl/andrea-nunez-bustamante/"},
        {"Marisol Agurto Merino",      "Médica Veterinaria",   "https://cmvlosrobles.cl/marisol-agurto-merino/"},
        {"Kevin Sura",                 "Médico Veterinario",   "https://cmvlosrobles.cl/kevin-sura-galdames/"},
        {"Claudia Rivera Bravo",       "Médica Veterinaria",   "https://cmvlosrobles.cl/claudia-rivera-bravo/"},
        {"Miguel Vergara Martinez",    "Administración",       "https://cmvlosrobles.cl/miguel-vergara-martinez/"},
        {"Consuelo Pérez Miranda",     "Médica Veterinaria",   "https://cmvlosrobles.cl/consuelo-perez-miranda/"},
        {"Rocío Aravena Ávila",        "Médica Veterinaria",   "https://cmvlosrobles.cl/rocio-aravena-avila/"},
        {"Fernanda Rojas Valenzuela",  "Médica Veterinaria",   "https://cmvlosrobles.cl/fernanda-rojas-valenzuela/"},
        {"Camila Sánchez González",    "Médica Veterinaria",   "https://cmvlosrobles.cl/camila-sanchez-gonzalez/"},
        {"Fabian Hidalgo Sandoval",    "Médica Veterinaria",   "https://cmvlosrobles.cl/fabian-hidalgo-sandoval/"},
    };

    private NuestroEquipoPage equipoPage;

    @BeforeMethod
    public void goToEquipoPage() {
        driver.get(EQUIPO_URL);
        equipoPage = new NuestroEquipoPage(driver);
    }

    // =========================================================
    // LISTING PAGE
    // =========================================================

    @Story("Listing Page")
    @Test(description = "Nuestro Equipo page heading is displayed")
    public void testPageHeading() {
        Assert.assertTrue(equipoPage.isPageHeadingDisplayed(), "Page heading not displayed");
        Assert.assertEquals(equipoPage.getPageHeadingText(), "Nuestro Equipo",
                "Page heading text mismatch");
    }

    @Story("Listing Page")
    @Test(description = "Listing shows exactly 15 team member cards")
    public void testTeamMemberCount() {
        Assert.assertEquals(equipoPage.getTeamMemberCount(), 15,
                "Expected 15 team member cards, found: " + equipoPage.getTeamMemberCount());
    }

    @Story("Listing Page")
    @Test(description = "Every card shows a loaded profile image")
    public void testAllCardsHaveLoadedImages() {
        List<Boolean> loaded = equipoPage.getAllCardImageLoadStatus();
        for (int i = 0; i < loaded.size(); i++) {
            Assert.assertTrue(loaded.get(i),
                    "Card #" + (i + 1) + " (" + equipoPage.getAllCardTitles().get(i) + ") has a missing or broken image");
        }
    }

    @Story("Listing Page")
    @Test(description = "Every card shows a non-empty name title")
    public void testAllCardsHaveTitles() {
        List<String> titles = equipoPage.getAllCardTitles();
        for (int i = 0; i < titles.size(); i++) {
            Assert.assertFalse(titles.get(i).isEmpty(),
                    "Card #" + (i + 1) + " has an empty title");
        }
    }

    @Story("Listing Page")
    @Test(description = "Every card shows a non-empty subtitle/role text")
    public void testAllCardsHaveSubtitles() {
        List<String> subtitles = equipoPage.getAllCardSubtitles();
        for (int i = 0; i < subtitles.size(); i++) {
            Assert.assertFalse(subtitles.get(i).isEmpty(),
                    "Card #" + (i + 1) + " (" + equipoPage.getAllCardTitles().get(i) + ") has an empty subtitle");
        }
    }

    @Story("Listing Page")
    @Test(description = "All 15 members appear in the listing with correct names")
    public void testAllExpectedMembersPresent() {
        List<String> actualTitles = equipoPage.getAllCardTitles();
        for (Object[] member : TEAM_MEMBERS) {
            Assert.assertTrue(actualTitles.contains(member[0]),
                    "Member not found in listing: " + member[0]);
        }
    }

    @Story("Listing Page")
    @Test(description = "Divi link data contains correct profile URL for every card")
    public void testAllCardProfileUrls() {
        List<String> actualUrls = equipoPage.getProfileUrls();
        for (Object[] member : TEAM_MEMBERS) {
            String expectedUrl = (String) member[2];
            Assert.assertTrue(actualUrls.contains(expectedUrl),
                    "Profile URL missing for " + member[0] + ": " + expectedUrl);
        }
    }

    // =========================================================
    // PROFILE SUB-PAGES  (data-driven, one test per member)
    // =========================================================

    @DataProvider(name = "teamMembers")
    public Object[][] teamMembersProvider() {
        return TEAM_MEMBERS;
    }

    @Story("Profile Page")
    @Test(
        dataProvider = "teamMembers",
        description = "Profile page loads and shows name, image, and role"
    )
    public void testProfilePage(String expectedName, String expectedRole, String profileUrl) {
        driver.get(profileUrl);
        TeamMemberPage profilePage = new TeamMemberPage(driver);

        // Page loaded at correct URL
        Assert.assertTrue(driver.getCurrentUrl().contains(
                profileUrl.replace("https://cmvlosrobles.cl", "")),
                "Profile page URL mismatch for " + expectedName);

        // Profile photo present and loaded
        Assert.assertTrue(profilePage.isProfileImageDisplayed(),
                "Profile image not displayed for: " + expectedName);
        Assert.assertTrue(profilePage.isProfileImageLoaded(),
                "Profile image broken (naturalWidth=0) for: " + expectedName);
        Assert.assertFalse(profilePage.getProfileImageSrc().isEmpty(),
                "Profile image src empty for: " + expectedName);

        // Name heading — profile h1 may include "Dra./Dr." prefix and full surname compound;
        // match by the first surname (last token of the card name)
        Assert.assertTrue(profilePage.isNameHeadingDisplayed(),
                "Name heading not displayed for: " + expectedName);
        String[] nameParts = expectedName.split(" ");
        String firstSurname = nameParts[nameParts.length - 2]; // second-to-last token = first surname
        Assert.assertTrue(profilePage.getNameHeadingText().contains(firstSurname),
                "Name heading does not contain first surname '" + firstSurname
                + "'. Found: " + profilePage.getNameHeadingText());

        // Role / specialty — content includes degree title or specialty description
        Assert.assertTrue(profilePage.isRoleTextDisplayed(),
                "Role text not displayed for: " + expectedName);
        String roleFound = profilePage.getRoleText();
        Assert.assertFalse(roleFound.isEmpty(),
                "Role/content text is empty for: " + expectedName);
    }
}
