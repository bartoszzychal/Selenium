package angularPage.PageTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import angularPage.DriverFactory.DriverFactory;
import angularPage.DriverFactory.DriverType;
import angularPage.Page.DialogAPage;
import angularPage.abstractObjects.AbstractSelenium;

@RunWith(Parameterized.class)
public class DialogASeleniumTest extends AbstractSelenium{

	private DialogAPage dialogAPage;
	private DriverType driverType;
	
	@Parameters
	public static Collection<DriverType[]> data() {
		DriverType[][] driver = new DriverType[][] { { DriverType.Chrome },{ DriverType.InternetExplorer },{ DriverType.Opera } };
	    return Arrays.asList(driver);
	}
	
	public DialogASeleniumTest(DriverType driverType) {
		this.driverType = driverType;
	}
	
	@Override
	@Before
	public void setUp() {
		WebDriver driver = null;
		driver = DriverFactory.getDriver(driverType);
		setDriver(driver);
		super.setUp();
		dialogAPage = openDialogAPage();
	}
	
	@Test
	public void editButtonShouldBeNotPresent() {
		//when
		Boolean editButtonIsPresent = dialogAPage.editButtonIsPresent();
		//then
		assertFalse(editButtonIsPresent);
	}
	
	@Test
	public void editButtonShouldBeNotEnable() {
		//when
		Boolean editButtonIsEnable = dialogAPage.editButtonIsEnabled();
		//then
		assertFalse(editButtonIsEnable);
	}
	
	@Test
	public void editButtonShouldBeEnableAfterARowClick() throws InterruptedException {
		//given
		int countOfRow = dialogAPage.getCountOfRow();
		int lastRow = countOfRow-1;
		//when
		dialogAPage.clickRowOfTable(lastRow);
		//then
		Boolean editButtonIsEnable = dialogAPage.editButtonIsEnabled();
		assertTrue(editButtonIsEnable);
	}
	
	@Test
	public void editButtonShouldBePresentAfterARowClick() throws InterruptedException {
		//given
		int countOfRow = dialogAPage.getCountOfRow();
		int lastRow = countOfRow-1;
		//when
		dialogAPage.clickRowOfTable(lastRow);
		//then
		Boolean editButtonIsDisplayed = dialogAPage.editButtonIsPresent();
		assertTrue(editButtonIsDisplayed);
	}
	@Test
	public void editButtonShouldBeNotEnableAfterDoubleClickTheSameRow() throws InterruptedException {
		//given
		int countOfRow = dialogAPage.getCountOfRow();
		int lastRow = countOfRow-1;
		//when
		dialogAPage.clickRowOfTable(lastRow).clickRowOfTable(lastRow);
		//then
		Boolean editButtonIsEnable = dialogAPage.editButtonIsEnabled();
		assertFalse(editButtonIsEnable);
	}
	@Test
	public void editButtonShouldBeNotPresentAfterDoubleClickTheSameRow() throws InterruptedException {
		//given
		int countOfRow = dialogAPage.getCountOfRow();
		int lastRow = countOfRow-1;
		//when
		dialogAPage.clickRowOfTable(lastRow).clickRowOfTable(lastRow);
		//then
		Boolean editButtonIsPresent = dialogAPage.editButtonIsPresent();
		assertFalse(editButtonIsPresent);
	}
	
	@Test
	public void addButtonShouldBeEnable() {
		//when
		Boolean addButtonIsEnable = dialogAPage.addButtonIsEnabled();
		//then
		assertTrue(addButtonIsEnable);
	}
	
	@Test
	public void addButtonShouldBePresent(){
		//when
		Boolean addButtonIsPresent = dialogAPage.addButtonIsPresent();
		//then
		assertTrue(addButtonIsPresent);
	}
	
	@Test
	public void editModalDialogShouldBeDisplayed() throws InterruptedException {
		///given
		int countOfRow = dialogAPage.getCountOfRow();
		int lastRow = countOfRow-1;
		//when
		dialogAPage.clickRowOfTable(lastRow).clickEditButton();
		//then
		Boolean modalDialogIsDisplayed = dialogAPage.modalDialogIsDisplayed();
		assertTrue(modalDialogIsDisplayed);
	}
	
	@Test
	public void addModalDialogShouldBeDisplayed() throws InterruptedException {
		//when
		dialogAPage.clickAddButton();
		//then
		Boolean modalDialogIsDisplayed = dialogAPage.modalDialogIsDisplayed();
		assertTrue(modalDialogIsDisplayed);
	}
	
	@Test
	public void lastClickedRowShouldBeActive() throws InterruptedException {
		int countOfRow = dialogAPage.getCountOfRow();
		int lastRow = countOfRow-1;
		int clickedRow = dialogAPage.clickRowOfTable(lastRow-1).clickRowOfTable(lastRow).getIndexOfClickedRow();
		assertEquals(lastRow, clickedRow);
	}
	
	@Test
	public void shouldClickAllRows() throws InterruptedException {
		int countOfRow = dialogAPage.getCountOfRow();
		for (int i = 0; i < countOfRow; i++) {
			dialogAPage.clickRowOfTable(i);
			int indexOfClickedRow = dialogAPage.getIndexOfClickedRow();
			assertEquals(i, indexOfClickedRow);
			Thread.sleep(1000);
		}
	}
	

}
