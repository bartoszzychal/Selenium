package angularPage.PageTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import angularPage.DriverFactory.DriverFactory;
import angularPage.DriverFactory.DriverType;
import angularPage.Page.DialogAPage;
import angularPage.abstractObjects.AbstractPageModalObject;
import angularPage.abstractObjects.AbstractSelenium;

@RunWith(Parameterized.class)
public class AddModalDialogSeleniumTest extends AbstractSelenium {
	
	private DialogAPage dialogAPage;
	private DriverType driverType;
	
	@Parameters
	public static Collection<DriverType[]> data() {
		DriverType[][] driver = new DriverType[][] { { DriverType.Chrome },{ DriverType.InternetExplorer },{ DriverType.Opera } };
	    return Arrays.asList(driver);
	}
	
	public AddModalDialogSeleniumTest(DriverType driverType) {
		this.driverType = driverType;
	}
	
	@Override
	@Before
	public void setUp() {
		WebDriver driver = DriverFactory.getDriver(driverType);
		setDriver(driver);
		super.setUp();
		dialogAPage = openDialogAPage();
	}

	
	@Test
	public void emptyTitleShouldBlockSaveButton() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog = dialogAPage.clickAddButton();
		//when
		addModalDialog.setTitle("").setAuthor("author").setGenre("it").setYear("2000");
		//then
		assertFalse(addModalDialog.confirmButtonIsEnable());
	}
	
	@Test
	public void emptyAuthorShouldBlockSaveButton() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog = dialogAPage.clickAddButton();
		//when
		addModalDialog.setTitle("title").setAuthor("").setGenre("it").setYear("2000");
		//then
		assertFalse(addModalDialog.confirmButtonIsEnable());
	}
	
	@Test
	public void emptyTitleAndAuthorShouldBlockSaveButton() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog =  dialogAPage.clickAddButton();
		//when
		addModalDialog.setTitle("").setAuthor("").setGenre("it").setYear("2000");
		//then
		assertFalse(addModalDialog.confirmButtonIsEnable());
	}
	
	@Test
	public void yearUnder1800ShouldBlockSaveButton() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog =  dialogAPage.clickAddButton();
		//when
		addModalDialog.setTitle("title").setAuthor("author").setGenre("it").setYear("1799");
		//then
		assertFalse(addModalDialog.confirmButtonIsEnable());
	}
	
	@Test
	public void yearHigherThenNextYearShouldBlockSaveButton() throws InterruptedException{
		//given
		int year = LocalDate.now().getYear()+2;
		AbstractPageModalObject addModalDialog =  dialogAPage.clickAddButton();
		//when
		addModalDialog.setTitle("title").setAuthor("author").setGenre("it").setYear(String.valueOf(year));
		//then
		assertFalse(addModalDialog.confirmButtonIsEnable());
	}
	
	@Test
	public void titleAndAuthorShouldBeEnoughToSave() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog = dialogAPage.clickAddButton();
		//when
		addModalDialog.setTitle("title").setAuthor("author").setGenre("").setYear("");
		//then
		assertTrue(addModalDialog.confirmButtonIsEnable());
	}
	
	@Test
	public void allDataShouldPermitSave() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog =  dialogAPage.clickAddButton();
		//when
		addModalDialog.setTitle("title").setAuthor("author").setGenre("it").setYear("2016");
		//then
		assertTrue(addModalDialog.confirmButtonIsEnable());
	}
	
	@Test
	public void cancelButtonShouldCloseModalDialog() throws InterruptedException{
		//given
		//when
		AbstractPageModalObject addModalDialog =  dialogAPage.clickAddButton();
		//then
		assertTrue(dialogAPage.modalDialogIsDisplayed());
		//when
		addModalDialog.clickCancelButton();
		//then
		assertFalse(dialogAPage.modalDialogIsDisplayed());
	}
	
	@Test
	public void dataShouldSaveAndPutOnTheLastPositionOfTable() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog = dialogAPage.clickAddButton();
		List<String> data = new ArrayList<String>();
		data.add("title");
		data.add("author");
		data.add("it");
		data.add("2017");
		//when
		addModalDialog.setTitle(data.get(0)).setAuthor(data.get(1)).setGenre(data.get(2)).setYear(data.get(3)).clickConfirmButton();
		List<String> editedRow = dialogAPage.getRow(dialogAPage.getCountOfRow()-1);
		//then
		assertArrayEquals(data.toArray(), editedRow.toArray());
	}
	
	@Test(timeout = 50000)
	public void allDataShouldSaveAndPutOnTheLastPositionOfTable() throws InterruptedException{
		//given
		AbstractPageModalObject addModalDialog;
		List<String> data = new ArrayList<String>();
		data.add("title");
		data.add("author");
		data.add("it");
		data.add("2017");
		int j = 30;
		//when
		for (int i = 0; i < j; i++) 
		{
			addModalDialog = dialogAPage.clickAddButton();
			addModalDialog.setTitle(data.get(0)).setAuthor(data.get(1)).setGenre(data.get(2)).setYear(data.get(3)).clickConfirmButton();
		}
	}
}
