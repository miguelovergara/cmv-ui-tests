package cl.cmvlosrobles.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class NosotrosPage {

    private WebDriver driver;

    @FindBy(css = "h1")
    private WebElement pageHeading;

    // Deduplicated paragraphs — Divi renders desktop + mobile copies; first() picks the first set
    @FindBy(xpath = "(//div[contains(@class,'et_pb_text_inner')]//p)[1]")
    private WebElement originParagraph;

    @FindBy(xpath = "(//div[contains(@class,'et_pb_text_inner')]//p)[2]")
    private WebElement foundingParagraph;

    @FindBy(xpath = "(//div[contains(@class,'et_pb_text_inner')]//p)[3]")
    private WebElement approachParagraph;

    @FindBy(xpath = "(//div[contains(@class,'et_pb_text_inner')]//p)[4]")
    private WebElement reachParagraph;

    @FindBy(xpath = "(//div[contains(@class,'et_pb_text_inner')]//p)[5]")
    private WebElement locationParagraph;

    @FindBy(xpath = "(//div[contains(@class,'et_pb_text_inner')]//p)[11]")
    private WebElement misionParagraph;

    @FindBy(xpath = "(//div[contains(@class,'et_pb_text_inner')]//p)[12]")
    private WebElement visionParagraph;

    // Clinic images by alt text
    @FindBy(xpath = "//img[@alt='frente casa antigua']")
    private WebElement clinicOldPhoto;

    @FindBy(xpath = "//img[@alt='foto frente casa acceso portón']")
    private WebElement clinicNewPhoto;

    // Feature images (animals)
    @FindBy(xpath = "(//img[contains(@src,'feature-1')])[1]")
    private WebElement featureImage1;

    @FindBy(xpath = "(//img[contains(@src,'feature-2')])[1]")
    private WebElement featureImage2;

    // All images in page content (for bulk load check)
    @FindBy(xpath = "//*[contains(@class,'et_pb_image_wrap')]/img[not(contains(@src,'logo')) and not(contains(@src,'instagram')) and not(contains(@src,'facebook'))]")
    private List<WebElement> contentImages;

    public NosotrosPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle()        { return driver.getTitle(); }
    public boolean isHeadingDisplayed() { return pageHeading.isDisplayed(); }
    public String  getHeadingText()     { return pageHeading.getText().trim(); }

    public String getOriginText()    { return originParagraph.getText().trim(); }
    public String getFoundingText()  { return foundingParagraph.getText().trim(); }
    public String getApproachText()  { return approachParagraph.getText().trim(); }
    public String getReachText()     { return reachParagraph.getText().trim(); }
    public String getLocationText()  { return locationParagraph.getText().trim(); }
    public String getMisionText()    { return misionParagraph.getText().trim(); }
    public String getVisionText()    { return visionParagraph.getText().trim(); }

    public boolean isClinicOldPhotoDisplayed() { return clinicOldPhoto.isDisplayed(); }
    public boolean isClinicOldPhotoLoaded() { return imageLoaded(clinicOldPhoto); }
    public String  getClinicOldPhotoAlt()    { return clinicOldPhoto.getAttribute("alt"); }

    public boolean isClinicNewPhotoDisplayed() { return clinicNewPhoto.isDisplayed(); }
    public boolean isClinicNewPhotoLoaded() { return imageLoaded(clinicNewPhoto); }
    public String  getClinicNewPhotoAlt()    { return clinicNewPhoto.getAttribute("alt"); }

    public boolean isFeatureImage1Loaded() { return imageLoaded(featureImage1); }
    public boolean isFeatureImage2Loaded() { return imageLoaded(featureImage2); }

    public List<Boolean> getAllContentImagesLoaded() {
        // scroll to trigger any lazy loading
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        return contentImages.stream().map(this::imageLoaded).collect(Collectors.toList());
    }

    private boolean imageLoaded(WebElement img) {
        Long w = (Long) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].naturalWidth;", img);
        return w != null && w > 0;
    }
}
