package starter.stepdefinitions.Users;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class UsersStepDefinitions {

    @Steps
    UsersStepLibrary users;
    public static String userId;


    @Given("Developer Search for User using {string}")
    public void Developer_Search_for_User_using_Username(String userName) {
        users.SearchForUserUsingUsername(userName);
    }

    @When("Developer Fetched User Id from API Response")
    public String Developer_Fetches_UserId_From_API_Response()
    {
        userId = users.GetUserId();
        return userId;
    }

    @When("Status Code should be {int}")
    public void Status_code_is_200(int statusCode) {
        users.StatusCode200(statusCode);
    }

    @Then("The Response should be empty")
    public void Response_Should_Be_Empty() {
        users.ResponseShouldBeEmpty();
    }
}
