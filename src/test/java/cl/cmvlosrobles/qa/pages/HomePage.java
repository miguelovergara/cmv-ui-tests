package cl.cmvlosrobles.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;

    // --- Top header ---
    @FindBy(xpath = "//div[@id='top-header']//a[contains(@href,'wa.me')]")
    private WebElement topHeaderPhone;

    @FindBy(xpath = "//div[@id='top-header']//a[contains(@href,'mailto:')]")
    private WebElement topHeaderEmail;

    @FindBy(xpath = "//div[@id='top-header']//a[contains(@href,'facebook')]")
    private WebElement topHeaderFacebook;

    @FindBy(xpath = "//div[@id='top-header']//a[contains(@href,'instagram')]")
    private WebElement topHeaderInstagram;

    // --- Navigation ---
    @FindBy(id = "logo")
    private WebElement logo;

    @FindBy(id = "et_search_icon")
    private WebElement searchIcon;

    @FindBy(xpath = "//ul[@id='top-menu']/li/a")
    private List<WebElement> mainMenuLinks;

    // --- Hero section (first #inicio section) ---
    @FindBy(xpath = "(//*[@id='inicio'])[1]//h1")
    private WebElement heroTitle;

    @FindBy(xpath = "(//*[@id='inicio'])[1]//p")
    private WebElement heroSubtitle;

    // --- Call / reservation button ---
    @FindBy(xpath = "//a[contains(., 'Haz tu reserva')]")
    private WebElement callButton;

    // --- WhatsApp floating button ---
    @FindBy(xpath = "//a[contains(@href, 'wa.me') or contains(@href, 'whatsapp')]")
    private WebElement whatsappButton;

    // --- Horario section ---
    @FindBy(xpath = "//*[@id='horario']//*[self::h1 or self::h2 or self::h3 or self::h4]")
    private WebElement horarioHeading;

    @FindBy(xpath = "(//*[@id='horario']//p)[1]")
    private WebElement horarioWeekdays;

    @FindBy(xpath = "(//*[@id='horario']//p)[2]")
    private WebElement horarioWeekend;

    // --- Nosotros section ---
    @FindBy(xpath = "(//*[@id='nosotros']//h2)[1]")
    private WebElement misionHeading;

    @FindBy(xpath = "(//*[@id='nosotros']//h2)[2]")
    private WebElement visionHeading;

    // --- Quote section (text-based to avoid index fragility) ---
    @FindBy(xpath = "//p[contains(.,'Mahatma Gandhi')]")
    private WebElement gandhiQuote;

    // --- Pacientes section ---
    @FindBy(xpath = "//h2[contains(.,'nuestros pacientes')]")
    private WebElement pacientesHeading;

    @FindBy(xpath = "//p[contains(.,'Envíanos la foto')]")
    private WebElement pacientesInvite;

    // --- Services section ---
    @FindBy(xpath = "//*[@id='services']//*[self::h2 or self::h3 or self::h4]")
    private List<WebElement> serviceHeadings;

    // --- Ubicacion section ---
    @FindBy(xpath = "//*[@id='ubicacion']//*[self::h1 or self::h2 or self::h3]")
    private WebElement ubicacionHeading;

    @FindBy(xpath = "//*[@id='ubicacion']//a[contains(@href,'maps')]")
    private WebElement ubicacionAddressLink;

    @FindBy(xpath = "//*[@id='ubicacion']//a[contains(@href,'wa.me')]")
    private WebElement ubicacionPhoneLink;

    @FindBy(xpath = "//*[@id='ubicacion']//a[contains(@href,'mailto:')]")
    private WebElement ubicacionEmailLink;

    // --- Legacy locators (kept for existing tests) ---
    @FindBy(xpath = "//ul[contains(@class, 'main-navigation')]//li/a")
    private List<WebElement> menuItems;

    @FindBy(xpath = "//h1[contains(., 'Los Robles')] | //h2[contains(., 'Los Robles')]")
    private WebElement mainTitle;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // --- Top header ---
    public boolean isTopHeaderPhoneDisplayed()   { return topHeaderPhone.isDisplayed(); }
    public String  getTopHeaderPhoneText()        { return topHeaderPhone.getText().trim(); }
    public String  getTopHeaderPhoneHref()        { return topHeaderPhone.getAttribute("href"); }

    public boolean isTopHeaderEmailDisplayed()   { return topHeaderEmail.isDisplayed(); }
    public String  getTopHeaderEmailText()        { return topHeaderEmail.getText().trim(); }
    public String  getTopHeaderEmailHref()        { return topHeaderEmail.getAttribute("href"); }

    public boolean isTopHeaderFacebookDisplayed() { return topHeaderFacebook.isDisplayed(); }
    public String  getTopHeaderFacebookHref()     { return topHeaderFacebook.getAttribute("href"); }

    public boolean isTopHeaderInstagramDisplayed() { return topHeaderInstagram.isDisplayed(); }
    public String  getTopHeaderInstagramHref()     { return topHeaderInstagram.getAttribute("href"); }

    // --- Navigation ---
    public boolean isLogoDisplayed()   { return logo.isDisplayed(); }
    public String  getLogoAlt()        { return logo.getAttribute("alt"); }
    public String  getLogoSrc()        { return logo.getAttribute("src"); }

    public boolean isSearchIconPresent() {
        // The search icon is a CSS-only icon with 0×0 bbox — check via JS instead
        Object result = ((JavascriptExecutor) driver).executeScript(
                "var el = document.querySelector('#et_search_icon, #et_top_search');" +
                "return el != null && window.getComputedStyle(el).display !== 'none';"
        );
        return Boolean.TRUE.equals(result);
    }

    public List<String> getMainMenuTexts() {
        return mainMenuLinks.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getMainMenuHrefs() {
        return mainMenuLinks.stream()
                .map(a -> a.getAttribute("href"))
                .collect(Collectors.toList());
    }

    // --- Hero ---
    public boolean isHeroTitleDisplayed()    { return heroTitle.isDisplayed(); }
    public String  getHeroTitleText()        { return heroTitle.getText().trim(); }
    public boolean isHeroSubtitleDisplayed() { return heroSubtitle.isDisplayed(); }
    public String  getHeroSubtitleText()     { return heroSubtitle.getText().trim().replace("\n", " "); }

    // --- Call / reservation button ---
    public boolean isCallButtonDisplayed() { return callButton.isDisplayed(); }
    public String  getCallButtonHref()     { return callButton.getAttribute("href"); }

    // --- WhatsApp button ---
    public boolean isWhatsappButtonDisplayed() { return whatsappButton.isDisplayed(); }
    public String  getWhatsappButtonHref()     { return whatsappButton.getAttribute("href"); }

    // --- Horario ---
    public boolean isHorarioSectionDisplayed()  { return horarioHeading.isDisplayed(); }
    public String  getHorarioHeadingText()       { return horarioHeading.getText().trim(); }
    public String  getHorarioWeekdaysText()      { return horarioWeekdays.getText().trim(); }
    public String  getHorarioWeekendText()       { return horarioWeekend.getText().trim(); }

    // --- Nosotros ---
    public boolean isMisionHeadingDisplayed() { return misionHeading.isDisplayed(); }
    public String  getMisionHeadingText()     { return misionHeading.getText().trim(); }
    public boolean isVisionHeadingDisplayed() { return visionHeading.isDisplayed(); }
    public String  getVisionHeadingText()     { return visionHeading.getText().trim(); }

    // --- Quote ---
    public boolean isGandhiQuoteDisplayed() { return gandhiQuote.isDisplayed(); }
    public String  getGandhiQuoteText()     { return gandhiQuote.getText().trim(); }

    // --- Pacientes ---
    public boolean isPacientesHeadingDisplayed() { return pacientesHeading.isDisplayed(); }
    public String  getPacientesHeadingText()     { return pacientesHeading.getText().trim(); }
    public String  getPacientesInviteText()      { return pacientesInvite.getText().trim(); }

    // --- Services ---
    public List<String> getServiceNames() {
        return serviceHeadings.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    // --- Ubicacion ---
    public boolean isUbicacionSectionDisplayed()  { return ubicacionHeading.isDisplayed(); }
    public String  getUbicacionHeadingText()       { return ubicacionHeading.getText().trim(); }
    public boolean isUbicacionAddressDisplayed()  { return ubicacionAddressLink.isDisplayed(); }
    public String  getUbicacionAddressText()       { return ubicacionAddressLink.getText().trim(); }
    public String  getUbicacionAddressHref()       { return ubicacionAddressLink.getAttribute("href"); }
    public String  getUbicacionPhoneHref()         { return ubicacionPhoneLink.getAttribute("href"); }
    public String  getUbicacionEmailHref()         { return ubicacionEmailLink.getAttribute("href"); }

    // --- Legacy ---
    public String       getMainTitleText()   { return mainTitle.getText(); }
    public List<String> getMenuItemsLabels() {
        return menuItems.stream()
                .map(WebElement::getText)
                .filter(t -> !t.isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
