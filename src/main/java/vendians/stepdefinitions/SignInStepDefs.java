package vendians.stepdefinitions;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import vendians.utils.DataPool;
import vendians.utils.DriverFactory;
import vendians.utils.PropertyReader;
import vendians.utils.UtilityMethods;

public class SignInStepDefs extends DriverFactory {


    @Given("^I am on the vendians home page$")
    public void launchHomePage() throws Throwable {
        String url = new PropertyReader().readProperty("appURL");
        driver.manage().window().maximize();
        driver.get(url);
        try {
            UtilityMethods.isLoaded();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UtilityMethods.waitForPageLoadAndPageReady();

        boolean Condition = false;
        String Evaluate = Boolean.toString(Condition);

        if ("true".equals(Evaluate)) {

            UtilityMethods.waitForPageLoadAndPageReady();
            UtilityMethods.waitForPageLoadAndPageReady();

            // Add PopUp Call for UserReg
            try {
                UtilityMethods.isLoaded();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Error e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        UtilityMethods.waitForPageLoadAndPageReady();
        driver.get(url);
        UtilityMethods.waitForPageLoadAndPageReady();
    }


    @When("^Click 'sign in' button$")
    public void i_click_sign_in_button() throws Throwable {
        WebElement element = driver.findElement(By.xpath("//div[@class='abcRioButtonContentWrapper']//span[@class='abcRioButtonContents']//span[1]"));
        element.click();
        UtilityMethods.waitForPageLoadAndPageReady();
    }


    @When("^Enter login creditionals$")
    public void enter_login_creditionals() throws Throwable {
        String parentWindowHandler = driver.getWindowHandle();
        UtilityMethods.switchToNewPopUpWindow(parentWindowHandler);
        UtilityMethods.waitForVisibility(driver.findElement(By.cssSelector("#identifierId")));
        driver.findElement(By.cssSelector("#identifierId")).clear();
        String email = new DataPool().readExcelData("data_file", "login_credentials", "email").get("Value").toString();
        String password = new DataPool().readExcelData("data_file", "login_credentials", "password").get("Value").toString();
        driver.findElement(By.cssSelector("#identifierId")).sendKeys(email);
        WebElement nextButton = driver.findElement(By.xpath("//div[@id='identifierNext']"));
        nextButton.click();
        UtilityMethods.waitForPageLoadAndPageReady();
        UtilityMethods.waitForPageLoadAndPageReady();
        UtilityMethods.waitForVisibility(driver.findElement(By.xpath("//input[@type='password' and @aria-label='Enter your password']")));
        driver.findElement(By.xpath("//input[@type='password' and @aria-label='Enter your password']")).clear();
        driver.findElement(By.xpath("//input[@type='password' and @aria-label='Enter your password']")).sendKeys(password);
        UtilityMethods.waitForClickable(driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")));
        driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")).click();;
        UtilityMethods.waitForPageLoadAndPageReady();
        UtilityMethods.waitForPageLoadAndPageReady();
        driver.close();
        driver.switchTo().window(parentWindowHandler);
        UtilityMethods.waitForPageLoadAndPageReady();
        UtilityMethods.switchToNewPopUpWindow(parentWindowHandler);
        driver.switchTo().frame(driver.findElement(By.cssSelector("body > iframe:nth-child(9)")));
        UtilityMethods.wait3Seconds();
        driver.findElement(By.id("continue-as")).click();
        UtilityMethods.switchToNewPopUpWindow(parentWindowHandler);
        getAllDates(parentWindowHandler);
    }

    public void getAllDates(String parentWindowHandler) throws Exception {
        try {
            UtilityMethods.wait3Seconds();
            UtilityMethods.waitForPageLoadAndPageReady();
            UtilityMethods.wait3Seconds();
            List<WebElement> alldates = driver.findElements(By.xpath("//span[@class='bar-container-add-text']"));
            UtilityMethods.wait3Seconds();
            System.out.println("alldates : " + alldates.size());
            for (int i = 1; i < alldates.size(); i++) {
                System.out.println("alldates : " + alldates.get(i).getText());
                if (alldates.get(i).getText().equals(String.valueOf(getCurrentDay()))) {
                    System.out.println("Days are same");
                    alldates.get(i).click();
                    UtilityMethods.waitForPageLoadAndPageReady();
                    setAllocationAndProject();
                    UtilityMethods.waitForPageLoadAndPageReady();
                }
                UtilityMethods.wait3Seconds();
            }
        } catch (Exception e) {
            System.out.print("Already Submitted.");
        }
    }

    public String getCurrentDay() {
        return "Your allocation for this day is due";
    }

    public void setAllocationAndProject() throws Exception {
        String project = new DataPool().readExcelData("data_file", "login_credentials", "project").get("Value").toString();
        String allocation = new DataPool().readExcelData("data_file", "login_credentials", "allocation").get("Value").toString();
        String parentWindowHandler = driver.getWindowHandle();
        UtilityMethods.switchToNewPopUpWindow(parentWindowHandler);
        driver.findElement(By.xpath("//div[@class='ant-select ant-select-enabled']")).click();
        driver.findElement(By.xpath("//input[@id='projectId']")).sendKeys(project);
        driver.findElement(By.xpath("//li[@class='ant-select-dropdown-menu-item ant-select-dropdown-menu-item-active']")).click();
        driver.findElement(By.xpath("//input[@id='allocation']")).sendKeys(allocation);
        driver.findElement(By.xpath("//button[@class='ant-btn ant-btn-primary']")).click();;

    }
}
