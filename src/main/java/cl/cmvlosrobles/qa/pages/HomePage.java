package cl.cmvlosrobles.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//ul[contains(@class, 'main-navigation')]//li/a")
    private List<WebElement> menuItems;

    @FindBy(xpath = "//h1[contains(., 'Los Robles')] | //h2[contains(., 'Los Robles')]")
    private WebElement mainTitle;

    @FindBy(xpath = "//a[contains(@href, 'tel:')]")
    private WebElement callButton;

    @FindBy(xpath = "//a[contains(@href, 'wa.me') or contains(@href, 'whatsapp')]")
    private WebElement whatsappButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getMainTitleText() {
        return mainTitle.getText();
    }

    public List<String> getMenuItemsLabels() {
        return menuItems.stream()
                .map(WebElement::getText)
                .filter(t -> !t.isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public boolean isCallButtonDisplayed() {
        return callButton.isDisplayed();
    }

    public String getCallButtonHref() {
        return callButton.getAttribute("href");
    }

    public boolean isWhatsappButtonDisplayed() {
        return whatsappButton.isDisplayed();
    }

    public String getWhatsappButtonHref() {
        return whatsappButton.getAttribute("href");
    }
}