package id.co.pkp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


        page.navigate("http://autopract.com/selenium/form5/");
        page.locator("input[value='CA']").click();
        page.locator("input[value='mac']").check();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Frame in Playwright Java")
    public void Handle_Frame_in_Playwright_Java(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://www.maths.surrey.ac.uk/explore/nigelspages/frame2.htm");

        FrameLocator middleFrame = page.frameLocator("//frame[@src='message.htm']");

        middleFrame.locator("//input[@name='name']").type("Naruto Uzumaki");
        middleFrame.locator("//textarea[@name='suggestions']").type("I Am Inside The Frame");

        page.close();
        browser.close();
        playwright.close();
        }

        @Test
    @DisplayName("Handle Nested Frames in Playwright Java")
    public void HandleNestedFramesinPlaywrightJava(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://the-internet.herokuapp.com/nested_frames");
            FrameLocator parentFrame = page.frameLocator("//frame[@name='frame-top']");
            FrameLocator middleFrame = parentFrame.frameLocator("//frame[@name='frame-middle']");
            String textContent = middleFrame.locator("body").textContent();
            System.out.println(textContent);

            page.close();
            browser.close();
            playwright.close();
        }

        @Test
    @DisplayName("Alert")
    public void Alert(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext newContext = browser.newContext(
                    new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
            Page page = newContext.newPage();
            page.navigate("http://autopract.com/selenium/alert5/");
            page.onDialog(dialog -> {
                dialog.accept();
            });
            page.locator("#alert-button").click();

            page.onDialog(dialog -> {
                dialog.dismiss();
            });
            page.locator("#confirm-button").click();


            newContext.close();
            playwright.close();
        }

        @Test
    @DisplayName("Pop-up")
    public void popup(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("http://autopract.com/selenium/popup/");

            Page popUp = page.waitForPopup(() -> {

                page.locator("//a[normalize-space()='Open Link in Popup']").click();

            });
            popUp.waitForLoadState();
            System.out.println(popUp.title());

            //popup modal
            page.navigate("http://autopract.com/selenium/popup/");

            page.locator("//a[normalize-space()='JQuery Popup Model']").click();

            String textContent = page.locator("//p[normalize-space()='This is Sample Popup.']").textContent();
            System.out.println(textContent);


            page.close();
            browser.close();
            playwright.close();
        }

        @Test
    @DisplayName("Record video")
    public void recordvideo(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext newContext = browser.newContext(
                    new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
            Page page = newContext.newPage();

            page.navigate("https://www.programsbuzz.com/user/login");

            page.locator("#edit-name").type("Nauto");
            page.locator("#edit-pass").type("Madara");

            newContext.close();
            playwright.close();
        }
        @Test
    @DisplayName("Download file")
    public void download_file(){
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("http://demo.automationtesting.in/FileDownload.html");
            Download waitForDownload = page.waitForDownload(page.locator("a.btn-primary")::click);
            waitForDownload.saveAs(Paths.get("Downloads/", waitForDownload.suggestedFilename()));


            System.out.println(waitForDownload.url());
            System.out.println(waitForDownload.page().title());
            System.out.println(waitForDownload.path().toString());

            page.close();
            browser.close();
            playwright.close();

        }
    @Test
    @DisplayName("Upload")
    public void uploadfile(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(700));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/upload1/");
        page.setInputFiles("//input[@type='file']",
                Paths.get("C:\\Users\\USER\\Pictures\\dede-inoen-raja-jin.png"));
        page.close();
        browser.close();
        playwright.close();
    }
    @Test
    @DisplayName("Upload2")
    public void uploadfile2(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(700));
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/upload");
        FileChooser fileChooser = page.waitForFileChooser(() -> page.click("#file-upload"));
        fileChooser.setFiles((Paths.get("C:\\Users\\USER\\Pictures\\dede-inoen-raja-jin.png")));
        page.click("input:has-text(\"Upload\")");
        page.waitForLoadState();
        System.out.println(page.locator("#upload-files").textContent());
        page.pause();
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Mouse hover element")
    public void mousehoverelemetn(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(700));
        Page page = browser.newPage();
        page.navigate("http://www.programsbuzz.com/");
        Locator tutorial = page.locator("//a[@class='we-mega-menu-li'][normalize-space()='Tutorials']");
        tutorial.hover();
        page.locator("//a[@class='we-mega-menu-li'][normalize-space()='Quality Assurance']").click();
        System.out.println(page.title());
        browser.close();
        playwright.close();

    }

    @Test
    @DisplayName("GET API")
    public void get_api (){
        int numberOfUsers = 10;
        Thread[] threads = new Thread[numberOfUsers];

        for (int i = 0; i < numberOfUsers; i++) {
            threads[i] = new Thread(() -> {
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                Page page = browser.newPage();
                Response response = page.navigate("https://devklinik.pkp.my.id/login");
                int status = response.status();
                System.out.println("Status code for user " + Thread.currentThread().getId() + ": " + status);
                page.close();
                browser.close();
                playwright.close();
            });
            threads[i].start();
        }

        // Wait for all threads to complete
        for (int i = 0; i < numberOfUsers; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    @DisplayName("POST API")
    public void post_api (){
        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        HashMap<String, String> data = new HashMap<String, String>();

        data.put("name", "Naruto");
        data.put("job", "Ninja");

        String response = request.post("https://reqres.in/api/users", RequestOptions.create().setData(data)).text();

        System.out.println(response);

        JsonObject j = new Gson().fromJson(response, JsonObject.class);
        System.out.println(j.get("name"));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("POST Multiple Data API")
    public void post_multiple_data_api (){
        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        // Membuat data pertama
        HashMap<String, String> data1 = new HashMap<String, String>();
        data1.put("name", "Naruto");
        data1.put("job", "Ninja");

        // Membuat data kedua
        HashMap<String, String> data2 = new HashMap<String, String>();
        data2.put("name", "Sasuke");
        data2.put("job", "Rogue Ninja");

        // Mengirimkan permintaan POST untuk data pertama
        String response1 = request.post("https://reqres.in/api/users", RequestOptions.create().setData(data1)).text();
        System.out.println("Response 1: " + response1);

        // Mengirimkan permintaan POST untuk data kedua
        String response2 = request.post("https://reqres.in/api/users", RequestOptions.create().setData(data2)).text();
        System.out.println("Response 2: " + response2);

        page.close();
        browser.close();
        playwright.close();
    }


    @Test
    @DisplayName("PUT API")
    public void put_api (){
        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        HashMap<String, String> data = new HashMap<String, String>();

        data.put("name", "Naruto");
        data.put("job", "Ninja");

        String response = request.put("https://reqres.in/api/users/2", RequestOptions.create().setData(data)).text();

        System.out.println(response);

        JsonObject j = new Gson().fromJson(response, JsonObject.class);
        System.out.println(j.get("name"));
        System.out.println(j.get("job"));
        page.close();
        browser.close();
        playwright.close();
    }

}
