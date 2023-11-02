import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.Assert.assertEquals;

public class RegistrationTest extends BaseTest{
	String emailText = "Mara." + randomAlphabetic(5) + "@blabla.com";
	String passwordText = "Blabla1";
	String randomText = randomAlphabetic(5);
	By signIn = By.cssSelector("[data-test='nav-sign-in']");
	By registerAccButton = By.cssSelector("[data-test='register-link']");
	By firstName = By.id("first_name");
	By lastName = By.id("last_name");
	By dateOfBirth = By.id("dob");
	By address = By.id("address");
	By postcode = By.id("postcode");
	By city = By.id("city");
	By state = By.id("state");
	By countryDropdown = By.id("country");
	By phone = By.id("phone");
	By email = By.id("email");
	By password = By.id("password");
	By submitButton = By.cssSelector(".btnSubmit");
	By loggedInUserName = By.id("user-menu");


	@Test
	public void registrationTest() throws InterruptedException {

		clickOnElement(signIn);
		driver.manage().timeouts().implicitlyWait(3, SECONDS);
		clickOnElement(registerAccButton);
		typeInput(firstName, randomText);
		typeInput(lastName, randomText);
		typeInput(dateOfBirth, "11.11.1911");
		typeInput(address, randomText);
		typeInput(postcode, randomText);
		typeInput(city, randomText);
		typeInput(state, randomText);

		Select objSelect =new Select(driver.findElement(countryDropdown));
		objSelect.selectByVisibleText("Serbia");

		typeInput(phone, "38164101010");
		typeInput(email, emailText);
		typeInput(password, passwordText);

		clickOnElement(submitButton);
		Thread.sleep(1000);

		typeInput(email, emailText);
		typeInput(password, passwordText);
		clickOnElement(submitButton);

		WebElement loggedInUser = driver.findElement(loggedInUserName);
		String actualLoggedInUser = loggedInUser.getText();
		String expectedLoggedInUser = randomText + " " + randomText;

		assertEquals(actualLoggedInUser, expectedLoggedInUser, "Users are not matching");
	}
}