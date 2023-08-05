package utilities;

import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CucumberHelper extends TestConfig {

	public HashMap<String, String> dataProperties = new HashMap<String, String>();
	public WebDriver driver;

	public CucumberHelper() {
		dataProperties = getPropertiesAsMap();
	}

//Below method creates a webdriver and opens the application url.
	public void createDriver() {
		try {
			switch (dataProperties.get("browser")) {
			case "chrome":
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--remote-allow-origins=*");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(option);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;

			}
			driver.get(dataProperties.get("url"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void closeBrowser() {
		driver.quit();
	}

	public void refreshBrowser() {
		driver.navigate().refresh();
	}

}
