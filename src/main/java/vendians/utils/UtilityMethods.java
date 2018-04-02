package vendians.utils;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityMethods extends DriverFactory {

    public static void wait3Seconds() throws InterruptedException {
        Thread.sleep(3000);
    }

    public static void waitForVisibility(WebElement element) {
        new WebDriverWait(driver, 0).until(ExpectedConditions.visibilityOf(element));
    }

    public static void wait3MinForElementInvisibility(String element) {
        new WebDriverWait(driver, 180).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(element)));
    }

    public static void waitForClickable(WebElement element) {
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void switchToNewPopUpWindow(String parentWindowHandler) throws InterruptedException {
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
        UtilityMethods.waitForPageLoadAndPageReady();
        System.out.println("on popup window .. driver.getTitle() = " + driver.getTitle());


    }

    public static void waitForPageLoadAndPageReady() {
        // wait2Seconds();
        waitForPageLoad();
        checkPageIsReady();
    }


    public static void isLoaded() throws Error, InterruptedException {
        waitForPageLoadAndPageReady();
        Thread.sleep(2000);
        waitForPageLoadAndPageReady();
    }


    public static void waitForPageLoad() {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                ((JavascriptExecutor) driver).executeScript("return document.readyState");
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
            }
        });
    }

    public static void checkPageIsReady() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String s = "";
        while (!s.equals("complete")) {
            s = (String) js.executeScript("return document.readyState");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
