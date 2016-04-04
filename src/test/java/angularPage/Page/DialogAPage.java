package angularPage.Page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import angularPage.abstractObjects.AbstractPageModalObject;
import angularPage.abstractObjects.AbstractPageObject;

public class DialogAPage extends AbstractPageObject {

	@FindBy(xpath="//button[@class='btn btn-warning']")
	private WebElement editButton;
	
	@FindBy(xpath="//button[@class='btn btn-success']")
	private WebElement addButton;
	
	//@FindBy(xpath= "/html/body/div[2]/div/section/table/tbody/tr")
	@FindBy(css = "table tbody tr")
	private List<WebElement> tableRows;
				
	public DialogAPage(WebDriver driver) {
		super(driver);
		this.driver.get("http://localhost:9000/#/component-1/dialog-a/");
	}


	public Boolean editButtonIsPresent() {
		try
        {
			editButton.isDisplayed();
            return true;
        }
        catch (NoSuchElementException ex)
        {
            return false;
        }
	}
	
	public Boolean addButtonIsPresent() {
		try
		{
			addButton.isDisplayed();
			return true;
		}
		catch (NoSuchElementException ex)
		{
			return false;
		}
	}
	
	public Boolean editButtonIsEnabled() {
		try
		{
			return editButton.isEnabled();
		}
		catch (NoSuchElementException ex)
		{
			return false;
		}
	}
	
	public Boolean addButtonIsEnabled() {
		try
		{
			return addButton.isEnabled();
		}
		catch (NoSuchElementException ex)
		{
			return false;
		}
	}
	
	public AbstractPageModalObject clickEditButton() throws InterruptedException {
		editButton.sendKeys(Keys.ENTER);
		Thread.sleep(500);
		return PageFactory.initElements(driver, EditModalDialog.class);
	}
	
	public AbstractPageModalObject clickAddButton() throws InterruptedException{
		addButton.sendKeys(Keys.ENTER);
		Thread.sleep(500);
		return PageFactory.initElements(driver, AddModalDialog.class);
	}
	
	public DialogAPage clickRowOfTable(int row) throws InterruptedException{
		if(row>-1 && tableRows.size()>row && tableRows.size()>0){
			Actions actions = new Actions(driver);
			actions.moveToElement(tableRows.get(row));
			actions.click();
			actions.build().perform();
		}
		return this;
	}
	
	public int getCountOfRow(){
		return tableRows.size();
	}
	
	public int getIndexOfClickedRow() throws InterruptedException{
		WebElement webElement = tableRows.stream().filter((element)->element.getAttribute("class").contains("active")).findFirst().get();
		return tableRows.indexOf(webElement);
	}
	
	public List<String> getRow(int index){
		return tableRows.get(index).findElements(By.tagName("td")).stream().map((element)->element.getText()).collect(Collectors.toList());	
	}
	
	public Boolean modalDialogIsDisplayed(){
		return driver.findElements(By.className("modal-dialog")).size() > 0;
	}

	
}
