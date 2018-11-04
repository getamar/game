package com.uk.stepdefinitions;

import com.uk.pages.UIPageObject;
import com.uk.utils.SharedDriver;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.junit.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class UIPageStepDefs {

    SharedDriver sharedDriver = new SharedDriver();
    UIPageObject uiPageObject = new UIPageObject(sharedDriver.driver);


    @Given("^the customer is in Virgin Home Page$")
    public void theCustomerIsInVirginHomePage() throws Throwable {
        sharedDriver.openURL();
    }

    @When("^the customer login with invalid userid - \"([^\"]*)\" and passord - \"([^\"]*)\"$")
    public void theCustomerLoginWithInvalidUseridAndPassord(String enterUserName, String enterPassword){
        uiPageObject.loginIn(enterUserName, enterPassword);
    }

    @Then("^I should get the error message as \"([^\"]*)\"$")
    public void iShouldGetTheErrorMessageAs(String expectedErrorMessage)  {
        assertEquals("Expected Error message is not same as Actual Error message",expectedErrorMessage,uiPageObject.getLoginErrorActualMessage());
    }

}
