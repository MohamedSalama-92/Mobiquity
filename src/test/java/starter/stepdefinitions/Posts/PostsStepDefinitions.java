package starter.stepdefinitions.Posts;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.stepdefinitions.Users.UsersStepDefinitions;
import starter.stepdefinitions.Users.UsersStepLibrary;

import java.util.List;

public class PostsStepDefinitions {
    @Steps
    PostsStepLibrary posts;

    public static List<Integer> postsidsList;

    @And("Developer Get all Posts using User Id")
    public void Developer_Search_for_User_using_Username() {
        posts.GetAllPostsRelatedToUserId(UsersStepDefinitions.userId);
    }

    @And("Developer Fetched Posts Ids List from API Response")
    public void Developer_Fetches_UserId_From_API_Response() {
        postsidsList = posts.FetchAllPostsIds();
    }

    @Given("Developer Search for posts using incorrect {string}")
    public void Developer_Get_Posts_Using_Invalid_User_Id(String userid){
        posts.GetAllPostsRelatedToUserId(userid);
    }

    @When("Status Code is {int}")
    public void Status_Code_Is_200(int statusCode) {
        posts.StatusCode200(statusCode);
    }

    @Then("The Response of the API should be empty")
    public void Response_Of_API_Should_Be_Empty() {
        posts.ResponseOfAPIShouldBeEmpty();
    }
}
