package cl.cmvlosrobles.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;

    @FindBy(css = ".main-navigation li a")
    private List<WebElement> menuItems;

    @FindBy(xpath = "//h1[contains(text(),'Centro Médico Veterinario Los Robles')]")
    private WebElement mainTitle;

    @FindBy(css = ".contact-info")
    private WebElement contactSection;

    @FindBy(linkText = "Servicios")
    private WebElement servicesMenuLink;

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
                .collect(Collectors.toList());
    }

    public void clickServices() {
        servicesMenuLink.click();
    }
}