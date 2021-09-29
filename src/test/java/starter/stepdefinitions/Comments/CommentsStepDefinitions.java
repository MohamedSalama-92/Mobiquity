package starter.stepdefinitions.Comments;

import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import starter.stepdefinitions.Posts.PostsStepDefinitions;
import starter.stepdefinitions.Posts.PostsStepLibrary;
import starter.stepdefinitions.Users.UsersStepDefinitions;

import java.util.List;

public class CommentsStepDefinitions {

    @Steps
    CommentsStepLibrary comments;

    @And("Developer use posts Ids List to get post's comments")
    public void Developer_Use_Posts_Ids_List_To_Get_Posts_Comments() {
        comments.GetAllCommentsRelatedtoPostId(PostsStepDefinitions.postsidsList);
    }

    @And("All Emails written in the comments should be in a correct format")
    public void Developer_Fetched_Emails_From_Reponse_And_Validate_Them() {
        Assert.assertTrue(comments.FetchAllemailsAndValidateThem());
    }
}
