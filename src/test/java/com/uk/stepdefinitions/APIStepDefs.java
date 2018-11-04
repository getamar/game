package com.uk.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class APIStepDefs {
    private static Response response;
    private String myJSONBody;
    private JSONObject obj = new JSONObject();

    @Given("^I have data value for \"([^\"]*)\" as \"([^\"]*)\" for login$")
    public void iHaveDataValueForAsForLogin(String getObjectName, String getObjectValue) throws Throwable {
        obj.put(getObjectName,getObjectValue);
    }

    @And("^I created the JSON File in (.*)$")
    public void iCreatedTheJSONFileInDynamicJSONDataJson(String enterJsonFileName) throws Throwable {
        createFile(obj);
        myJSONBody=generateStringFromResources(getJSONDataFilePath(enterJsonFileName));
    }

    @When("^the user request POST method against the URL (.*)$")
    public void iUserRequestPOSTMethodAgainstTheURLUpdate(String enterURLPath) throws Throwable {
        response=postMethodResponse(enterURLPath);
    }

    @Then("^the user will receive response code as (.*)$")
    public void iWillReceiveAnOKResponse(int getMeessageStatus) throws Throwable {
        assertEquals("Successful message is not received",getMeessageStatus,response.getStatusCode());
    }


    @Then("^the user should get \"([^\"]*)\" in the path \"([^\"]*)\"$")
    public void iShouldGetIsInThePath(String expectedValue, String path) throws Throwable {
        assertEquals("Expected value is not same as actual value",expectedValue,response.getBody().jsonPath().get(path).toString());
    }


    public void createFile(JSONObject obj){
        try(FileWriter file = new FileWriter("src/test/resources/Data/DynamicJSONData.json")){
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getJSONDataFilePath(String enterFileName){
        return new File(System.getProperty("user.dir")+
                "/src/test/resources/Data/"+enterFileName).getAbsolutePath();
    }

    public String generateStringFromResources(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public String getURLFromPropertyFile() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/uk/utils/Config.properties"));
        return properties.getProperty("BASE_URL");
    }
    public Response postMethodResponse(String getPath) throws IOException {
        return given()
                .header("Accept","*/*")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept-Language","en-GB,en-US;q=0.9,en;q=0.8")
                .contentType("application/json")
                .body(myJSONBody)
                .when()
                .post(getURLFromPropertyFile()+getPath);
    }

}