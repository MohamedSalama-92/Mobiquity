package starter.stepdefinitions.Comments;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import org.apache.commons.validator.routines.EmailValidator;
import starter.ObjectMapping.CommentsObjects;
import starter.ObjectMapping.PostsObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentsStepLibrary {

    private Response res = null; //Response
    private JsonPath jp = null; //JsonPath
    private RequestSpecification requestSpec;

    ResponseBody PostsIds;

    @Step
    public void GetAllCommentsRelatedtoPostId(List<Integer> postsidslist)
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath("/comments");
        for (int postid: postsidslist) {
            builder.addParam("postId",postid);
        }
        builder.setContentType("application/json");
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();
    }

    @Step
    public boolean FetchAllemailsAndValidateThem() {
        CommentsObjects[] comments = requestSpec.when().get().as(CommentsObjects[].class);
        List<CommentsObjects> commentsList = new ArrayList(Arrays.asList(comments));
        //List<String> emails = new ArrayList<String>();
        EmailValidator validator = EmailValidator.getInstance();

        for (CommentsObjects comment: commentsList) {
            if(validator.isValid(comment.getEmail()))
            {
                continue;
            }
            else
                return false;
        }
        return true;
    }
}
