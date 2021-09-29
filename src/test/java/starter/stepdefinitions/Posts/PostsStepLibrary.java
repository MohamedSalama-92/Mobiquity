package starter.stepdefinitions.Posts;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import starter.ObjectMapping.PostsObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostsStepLibrary {

    private Response res = null; //Response
    private JsonPath jp = null; //JsonPath
    private RequestSpecification requestSpec;

    @Step
    public void GetAllPostsRelatedToUserId(String userId)
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath("/posts");
        builder.addParam("userId",userId);
        builder.setContentType("application/json");
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();
    }

    @Step
    public List FetchAllPostsIds() {
        PostsObjects[] posts = requestSpec.when().get().as(PostsObjects[].class);
        List<PostsObjects> postsIdList = new ArrayList(Arrays.asList(posts));
        List<Integer> postsids = new ArrayList<Integer>();
        for (PostsObjects postid: postsIdList) {
            postsids.add(postid.getId());
        }

       return postsids;
    }

    @Step
    public void StatusCode200(int statusCode)
    {
        res = requestSpec.when().get();
        Assert.assertEquals( statusCode, res.getStatusCode());
    }
    @Step
    public void ResponseOfAPIShouldBeEmpty()
    {
        res = requestSpec.when().get();
        jp = new JsonPath(res.asString());
        Assert.assertEquals(("[]"),jp.getJsonObject("postId").toString());
    }
}
