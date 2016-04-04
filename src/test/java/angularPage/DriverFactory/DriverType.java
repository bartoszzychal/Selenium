package angularPage.DriverFactory;

public enum DriverType {
	
	Chrome("chromedriver.exe","webdriver.chrome.driver"), 
	Opera("operadriver.exe","webdriver.opera.driver"), 
	InternetExplorer("IEDriverServer.exe","webdriver.ie.driver");
	
	private String type;
	private String key;
	
	private DriverType(String type, String key) {
		this.type = type;
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}
}
