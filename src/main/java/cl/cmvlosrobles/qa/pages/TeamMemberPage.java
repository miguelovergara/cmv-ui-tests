package cl.cmvlosrobles.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamMemberPage {

    private WebDriver driver;

    // Profile photo - first non-logo image in the Divi image module
    @FindBy(css = ".et_pb_image_wrap img")
    private WebElement profileImage;

    // Full name heading
    @FindBy(css = "h1")
    private WebElement nameHeading;

    // Role / specialty — first Divi text module (some profiles use <p>, others use <ul><li>)
    @FindBy(css = ".et_pb_text_inner")
    private WebElement roleText;

    public TeamMemberPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isProfileImageDisplayed() {
        return profileImage.isDisplayed();
    }

    public boolean isProfileImageLoaded() {
        Long w = (Long) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].naturalWidth;", profileImage);
        return w != null && w > 0;
    }

    public String getProfileImageSrc() {
        return profileImage.getAttribute("src");
    }

    public boolean isNameHeadingDisplayed() {
        return nameHeading.isDisplayed();
    }

    public String getNameHeadingText() {
        return nameHeading.getText().trim();
    }

    public boolean isRoleTextDisplayed() {
        return roleText.isDisplayed();
    }

    public String getRoleText() {
        return roleText.getText().trim();
    }
}
