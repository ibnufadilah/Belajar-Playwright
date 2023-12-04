package id.co.pkp;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class app1 {
        @Test
        @DisplayName("Test Web PKP")
        public void test1() {
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
//        page.navigate("https://pkp.co.id");
            page.navigate("https://www.google.co.id/");
            System.out.println( "Page Title nya adalah: "+page.title());
        }

        @Test
        @DisplayName("Check URL or Check HTTPS")
        public void testCheckHTTPS(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("http://10.8.11.1:8761/");
            String currentUrl = page.url();
            String expectedUrl = "https://10.8.11.1:8761/";
            if (currentUrl.equals(expectedUrl)) {
                System.out.println("URL is correct: " + currentUrl);
            } else {
                System.out.println("URL is incorrect. Expected: " + expectedUrl + ", but got: " + currentUrl);
            }
            // System.out.println(currentUrl);
            browser.close();
            playwright.close();
        }

        @Test
        @DisplayName("Check Place Holder")
        public void checkPlaceHolder(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.programsbuzz.com/user/login");

            Locator searchBar = page.locator("#edit-keys--2");
            String placeText = searchBar.getAttribute("search");

            if (placeText.contains("Enter the terms you wish to search for")) {

                System.out.println("PASS");

            } else {
                System.out.println("FAIL! No such texts");
            }
        }

        @Test
        @DisplayName("Assert Checkbox")
        public void assertCheckBox(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("http://autopract.com/selenium/dropdown1/");
            Locator locator = page.locator("select.custom-select option >> nth=-2");
            String attributeV = locator.getAttribute("value");

            if (attributeV.equals("item3")) {
                System.out.println("Attribute value is correct!");
            } else {
                System.out.println("Attribute value is incorrect.");
            }
            browser.close();
            playwright.close();
        }
        @Test
        @DisplayName("Soft assertion")
        public void softassertion(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.programsbuzz.com/user/login");
            page.locator("#edit-name").type("nana");
            page.locator("#edit-pass").type("nnv");
            page.locator("(//input[@type='submit'])[2]").click();
            String actualText = page.locator("//a[normalize-space()='Forgot your password?']").textContent();
            System.out.println(actualText);
            String expectedText = "Forgot your password";
            SoftAssert soft = new SoftAssert();
            soft.assertEquals(actualText, expectedText, "Matched");

            System.out.println("This part is executed");
            soft.assertAll();
            page.close();
            browser.close();
            playwright.close();

        }
        @Test
        @DisplayName("Get first and last element")
        public void getfirstandlastelement(){
           //first element
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
//            Locator listEle = page.locator("//h3[@class='search-result__title']");
//            listEle.first().click();

            //last element
            page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
            Locator listEle = page.locator("//h3[@class='search-result__title']");
            listEle.nth(0).click();


            page.close();
            browser.close();
            playwright.close();
        }
    @Test
    @DisplayName("get list element")
    public void getlistelement(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle = page.locator("//h3[@class='search-result__title']");
        List<String> allTextContents = listEle.allTextContents();
        System.out.println(allTextContents);
        page.close();
        browser.close();
        playwright.close();

    }

    @Test
    @DisplayName("Dropdown")
    public void dropdown(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/dropdown1/");
        page.selectOption(".custom-select", "item2");

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Dynamic Dropdown")
    public void dynamindropdown(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/dropdown4/");
        page.locator("//span[@class='caret']").click();
        Locator countries = page.locator("//div[@role='combobox']");

        List<String> allInnerTexts = countries.allInnerTexts();
//        allInnerTexts.forEach(System.out::println);
        for (String innerTexts : allInnerTexts) {
            if (innerTexts.contains("Aruba")) {
                countries.click();
            }
        }
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Auto complete test")
    public void autco_complete_test(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        String expectedText = "Guatemala";

        page.navigate("https://demo.automationtesting.in/AutoComplete.html");
        Locator autoC = page
                .locator("//div[@class='ui-autocomplete-multiselect ui-state-default ui-widget ui-state-active']");
        int autoCcount = autoC.count();
        System.out.println("autoCcount: " + autoCcount);

        page.pause();

        for (int i = 0; i < autoCcount; i++) {

            String autoCText = autoC.nth(i).textContent();
            System.out.println("yang dicari" + autoCText);
            if (autoCText == expectedText) {

                autoC.nth(i).click();
                break;

            }
        }
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Checlbox")
    public void checkbox(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        // using click
        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='four']").click();

        //using check
        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='four']").check();

        // using uncheck
        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='four']").uncheck();

        page.close();
        browser.close();
        playwright.close();
    }



}
