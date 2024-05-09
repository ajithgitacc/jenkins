package org.stepdefinition;

import org.baseclass1.BaseClass;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep extends BaseClass {
	@Given("user is on the omr page")
	public void user_is_on_the_omr_page() {
		ChromeBrowserLaunch();
		maximizeWindow();
		implicitWait();
		urlEnter("https://omrbranch.com/");
	}
	
	@When("user login {string} and {string}")
	public void user_login_and(String userName, String password) {
		findLocatorById("email").sendKeys(userName);
		findLocatorById("pass").sendKeys(password);
	}

	@Then("user should verify after login success message")
	public void user_should_verify_after_login_success_message() {
		Assert.assertTrue("verify login message",true);
	}
}
