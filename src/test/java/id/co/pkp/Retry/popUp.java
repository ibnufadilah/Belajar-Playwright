package id.co.pkp.Retry;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class popUp {
    private Browser browser;
    private Page page;
    @BeforeTest
    private void setUp() throws InterruptedException{
        try (Playwright playwright = Playwright.create()){
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            page = browser.newPage();
            page.navigate("https://programsbuzz.com");
        }
    }
    @AfterTest
    private void tearDown() { browser.close(); }

    @Test(retryAnalyzer = dicoba.class)
    public void popUp() throws Throwable{
        page.locator("//i[@class='fa fa-search']").click();
        page.locator("//input[@id='edit-keys']").type("Playwright");
        page.locator("//input[@id'edit-submit']").click();
        page.goBack();
        page.goForward();
    }
}
