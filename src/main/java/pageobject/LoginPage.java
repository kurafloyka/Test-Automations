package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;

	By email = By.id("EmailLogin");
	By password = By.id("Password");
	By submit = By.xpath("//*[@id=\"userLogin\"]/div[6]/button");
	By failLogin = By.xpath("//*[@id=\"modalBox\"]/div/div/div[2]");

	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getEmail() {
		return driver.findElement(email);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getSubmit() {
		return driver.findElement(submit);
	}

	public WebElement getFailLogin() {
		return driver.findElement(failLogin);
	}

}
