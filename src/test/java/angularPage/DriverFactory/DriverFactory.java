package angularPage.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	
	private static String driversPath = "C:\\Users\\ZBARTOSZ\\workspace_selenium\\my_selenium-testing\\drivers\\";
	
	private DriverFactory(){	
	}
	
	public static WebDriver getDriver(DriverType driverType){
		WebDriver driver = null;
		System.setProperty(driverType.getKey(), driversPath+driverType.getType());
		
		switch (driverType) {
		case Chrome:
			driver = new ChromeDriver();
			break;
		case Opera:
			driver = new OperaDriver();
			break;
		case InternetExplorer:
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(
			    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			    true);
			caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			driver = new InternetExplorerDriver(caps);
			break;

		default:
			break;
		}
		
		return driver;
	}
}
