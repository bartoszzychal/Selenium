package angularPage.PageTestNG;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import angularPage.DriverFactory.DriverFactory;
import angularPage.DriverFactory.DriverType;
import angularPage.Page.DialogAPage;
import angularPage.abstractObjects.AbstractPageModalObject;
import angularPage.abstractObjects.AbstractSelenium;

public class AddModalDialogSeleniumTestNG extends AbstractSelenium{
	
	private DialogAPage dialogAPage;
	
	@BeforeSuite
	public void setUp() {
		setDriver(DriverFactory.getDriver(DriverType.Chrome));
		super.setUp();
		dialogAPage = openDialogAPage();
	}
	

	@Test(invocationCount = 10, threadPoolSize = 1)
	public void allDataShouldSaveAndPutOnTheLastPositionOfTable() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog;
		List<String> data = new ArrayList<String>();
		data.add("title");
		data.add("author");
		data.add("it");
		data.add("2017");
		//when
		addModalDialog = dialogAPage.clickAddButton();
		addModalDialog.setTitle(data.get(0)).setAuthor(data.get(1)).setGenre(data.get(2)).setYear(data.get(3)).clickConfirmButton();
	}
	
	
	@AfterSuite
	public void thearDown() {
		super.thearDown();
	}
}
