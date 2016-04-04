package angularPage.abstractObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AbstractPageModalObject {
	
	protected WebDriver driver;
	
	@FindBy(className="modal-dialog")
	private WebElement modalDialog;
	
	@FindBy(xpath="/html/body/div[4]/div/div/div/div[3]/button[1]")
	private WebElement confirmButton;

	@FindBy(xpath="/html/body/div[4]/div/div/div/div[3]/button[2]")
	private WebElement cancelButton;
	
	public AbstractPageModalObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<String> getDialogInputsText(){
		return modalDialog.findElements(By.tagName("input")).stream()
				.map((element)->element.getAttribute("value"))
				.collect(Collectors.toList());
	}
	
	public AbstractPageModalObject setTitle(String title){
		WebElement titleElement = modalDialog.findElement(By.name("title"));
		titleElement.clear();
		titleElement.sendKeys(title);
		return this;
	}
	public AbstractPageModalObject setAuthor(String author){
		WebElement authorElement = modalDialog.findElement(By.name("author"));
		authorElement.clear();
		authorElement.sendKeys(author);
		return this;
	}
	public AbstractPageModalObject setGenre(String genre){
		WebElement genreElement = modalDialog.findElement(By.name("genre"));
		genreElement.clear();
		genreElement.sendKeys(genre);
		return this;
	}
	public AbstractPageModalObject setYear(String year){
		WebElement yearElement = modalDialog.findElement(By.name("year"));
		yearElement.clear();
		yearElement.sendKeys(year);
		return this;
	}
	
	public Boolean confirmButtonIsEnable(){
		return confirmButton.isEnabled();
	}
	
	public void clickConfirmButton() throws InterruptedException{
		confirmButton.sendKeys(Keys.ENTER);
		Thread.sleep(500);
	}
	
	public void clickCancelButton() throws InterruptedException{
		cancelButton.sendKeys(Keys.ENTER);;
		Thread.sleep(500);
	}
}
