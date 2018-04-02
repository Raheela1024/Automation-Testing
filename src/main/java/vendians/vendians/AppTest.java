package vendians.vendians;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import vendians.utils.DriverFactory;

@RunWith(Cucumber.class)
@CucumberOptions(


    tags = {"@C104"},
    features = {"resources/features/"},
    glue = {"vendians.stepdefinitions"},
    plugin = {})

public class AppTest extends DriverFactory {
 
    @BeforeClass
    public static void start() {}

    @AfterClass
    public static void tearDown() throws IOException, InterruptedException {
       quitDriver();
    }

    public static void main(String[] args) throws Throwable {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(AppTest.class); 
        if (result.getFailureCount() > 0) {
          System.out.println("Vendians update failed.");
          System.exit(1);
        } else {
          System.out.println("Vendians updated successfully.");
          System.exit(0);
        }
    }
}


