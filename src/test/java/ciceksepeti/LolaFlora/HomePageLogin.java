package ciceksepeti.LolaFlora;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import junit.framework.Assert;
import pageobject.LoginPage;
import resources.Base;

public class HomePageLogin extends Base {

	public static String failLogIn = "E-mail address or password is incorrect. Please check your information and try again.";
	ExtentReports reports;
	ExtentHtmlReporter htmlReporter;
	ExtentTest test;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		reports = new ExtentReports();

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//extentreport.html");
		reports.attachReporter(htmlReporter);

		reports.setSystemInfo("Machine", "FARUK-AKYOL");
		reports.setSystemInfo("Env", "DevOps");
		reports.setSystemInfo("Build", "Integration");
		reports.setSystemInfo("Browser", "IE");

		driver = initializeDriver();

	}

	@Test
	public void SuccessLogin() throws InterruptedException {

		test = reports.createTest("Correctlogin");

		driver.get("https://www.lolaflora.com/login");

		log.info("Website is opened");

		Thread.sleep(2000L);

		LoginPage lgn = new LoginPage(driver);

		lgn.getEmail().sendKeys("farukakyol441@gmail.com");
		log.info("Username is written");

		lgn.getPassword().sendKeys("faruk123456789");
		log.info("Password is written");

		lgn.getSubmit().click();
		log.info("Button is clicked");

		Thread.sleep(5000L);
		test.pass("login test passed");

	}

	@Test
	public void FailLogin() throws InterruptedException {

		test = reports.createTest("faillogin");

		driver.get("https://www.lolaflora.com/login");

		log.info("Website is opened");

		Thread.sleep(2000L);

		LoginPage lgn = new LoginPage(driver);

		lgn.getEmail().sendKeys("WrongEmail@gmail.com");
		log.info("Username is written");

		lgn.getPassword().sendKeys("123456789");
		log.info("Password is written");

		lgn.getSubmit().click();
		log.info("Button is clicked");

		Thread.sleep(2000L);

		if (lgn.getFailLogin().getText().equals(failLogIn)) {

			Assert.assertTrue(true);
			test.pass("failedLogin is successed");

		}

	}
	// eger pass arkadası fail denersen onun ekran goruntusunu alir.

	@Test
	public void fail() {

		test = reports.createTest("fail");
		Assert.assertTrue(false);
		test.pass("test failed");

	}

	/*
	 * @Test public void skipTest() {
	 * 
	 * test = reports.createTest("skipTest"); throw new
	 * SkipException("Skip test forecefully");
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * public void ScreenShot() {
	 * 
	 * test = reports.createTest("ScreenShot");
	 * 
	 * driver.get("https://www.lolaflora.com/login");
	 * 
	 * log.info("Website is opened");
	 * 
	 * Assert.assertTrue(false); test.pass("test failed");
	 * 
	 * }
	 */
	@AfterMethod
	public void setTestResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			test.fail(result.getName());
			test.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {

			test.skip("Test Case :" + result.getName() + "has been skipped");

		}

	}

	@AfterTest
	public void tearDown() {

		driver.close();
		driver = null;

		reports.flush();
		// driver.quit();

	}
}
