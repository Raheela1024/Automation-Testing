package vendians.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

	protected static WebDriver driver;

	public DriverFactory()   {
		initialize();
	}

	public void initialize()  {
		if (driver == null)
			if(new PropertyReader().readProperty("runAt").equals("local"))
			{
				createNewLocalDriverInstance();
			}
			
	}

	private void createNewLocalDriverInstance()   {
		String browser = new PropertyReader().readProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
 
			ChromeOptions options = new ChromeOptions(); 
			String browserExePath = new PropertyReader().readProperty("browserExePath");
			String driverExePath = new PropertyReader().readProperty("driverExePath");
			
			options.setBinary(browserExePath); 
			System.setProperty("webdriver.chrome.driver", driverExePath); 
			driver = new ChromeDriver(options);
			
			
		}  else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
	}


	public WebDriver getDriver() {
		return driver;
	}

	public static void quitDriver() {
		driver.quit();
		driver = null;
	}
}
