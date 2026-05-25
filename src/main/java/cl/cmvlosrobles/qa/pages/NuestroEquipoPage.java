package cl.cmvlosrobles.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class NuestroEquipoPage {

    private WebDriver driver;

    // Page H1 "Nuestro Equipo" — uses h1 to distinguish from blurb h4 headers
    @FindBy(css = "h1.et_pb_module_header")
    private WebElement pageHeading;

    // One .et_pb_blurb per team member (15 total)
    @FindBy(css = ".et_pb_blurb")
    private List<WebElement> teamMemberBlurbs;

    public NuestroEquipoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageHeadingDisplayed() {
        return pageHeading.isDisplayed();
    }

    public String getPageHeadingText() {
        return pageHeading.getText().trim();
    }

    public int getTeamMemberCount() {
        return teamMemberBlurbs.size();
    }

    public List<String> getAllCardTitles() {
        return teamMemberBlurbs.stream()
                .map(b -> b.findElement(By.cssSelector(".et_pb_module_header")).getText().trim())
                .collect(Collectors.toList());
    }

    public List<String> getAllCardSubtitles() {
        return teamMemberBlurbs.stream()
                .map(b -> b.findElement(By.cssSelector(".et_pb_blurb_description")).getText().trim())
                .collect(Collectors.toList());
    }

    public List<Boolean> getAllCardImageLoadStatus() {
        // Scroll the full page so lazy-loaded images below the fold get fetched
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        return teamMemberBlurbs.stream()
                .map(b -> {
                    List<WebElement> imgs = b.findElements(By.cssSelector(".et_pb_main_blurb_image img"));
                    if (imgs.isEmpty()) return false;
                    Long w = (Long) ((JavascriptExecutor) driver)
                            .executeScript("return arguments[0].naturalWidth;", imgs.get(0));
                    return w != null && w > 0;
                })
                .collect(Collectors.toList());
    }

    // Reads profile URLs from Divi's inline JS variable diviElementLinkData
    @SuppressWarnings("unchecked")
    public List<String> getProfileUrls() {
        return (List<String>) ((JavascriptExecutor) driver).executeScript(
                "return (window.diviElementLinkData || [])" +
                ".filter(function(d){ return /^et_pb_blurb_\\d+$/.test(d.class); })" +
                ".map(function(d){ return d.url; });"
        );
    }
}
