package cl.cmvlosrobles.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "https://cmvlosrobles.cl/";

    // Milliseconds to wait after page navigation before interacting with the page
    protected static final long PAGE_LOAD_WAIT_MS = 1000;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Detect if running in GitHub Actions or other CI environment
        boolean isCI = System.getenv("GITHUB_ACTIONS") != null || System.getProperty("headless") != null;

        if (isCI) {
            // Mandatory flags for Linux/Containerized environments
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        } else {
            // maximized option for local execution
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        navigateTo();
    }

    protected void navigateTo() {
        navigateTo("");
    }

    protected void navigateTo(String path) {
        navigateToUrl(BASE_URL + path);
    }

    protected void navigateToUrl(String url) {
        driver.get(url);
        try {
            Thread.sleep(PAGE_LOAD_WAIT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}