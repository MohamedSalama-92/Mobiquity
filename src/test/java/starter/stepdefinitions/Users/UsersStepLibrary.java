package starter.stepdefinitions.Users;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;

public class UsersStepLibrary {

    private Response res = null; //Response
    private JsonPath jp = null; //JsonPath
    private RequestSpecification requestSpec;

    private String userid;

    @Step
    public void SearchForUserUsingUsername(String userName)
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath("/users");
        builder.addParam("username",userName);
        builder.setContentType("application/json");
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();
    }

    @Step
    public void StatusCode200(int statusCode)
    {
        res = requestSpec.when().get();
        Assert.assertEquals( statusCode, res.getStatusCode());
    }

    @Step
    public void ResponseShouldBeEmpty()
    {
        res = requestSpec.when().get();
        jp = new JsonPath(res.asString());
        Assert.assertEquals(("[]"),jp.getJsonObject("username").toString());
    }

    @Step
    public String GetUserId()
    {
        res = requestSpec.when().get();
        jp = new JsonPath(res.asString());
        jp = res.jsonPath();
        userid = jp.getJsonObject("id").toString().replace("[","").replace("]","");
        return userid;
    }

    /*@Step
    public void HittingAPIWithoutQueries()
    {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath("/search.json");
        builder.setContentType("application/json");
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();
    }
    @Step
    public void ErrorMessageShouldBeDisplayed()
    {
        jp = new JsonPath(res.asString());
        jp.getJsonObject("results");
        Assert.assertEquals("Failed to resolve API Key variable request.queryparam.api-key",jp.getJsonObject("fault.faultstring").toString().replace("[","").replace("]",""));
    }

    @Step
    public void UserEnterAPIkey(String apiKey)
    {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath("/search.json");
        builder.addParam("api-key",apiKey);
        builder.setContentType("application/json");
        requestSpec = builder.build();
        requestSpec = RestAssured.given().spec(requestSpec);
        requestSpec.log().all();
    }

    @Step
    public void ValidateAllResponse()
    {
        res.then()
                .assertThat()
                .body(Matchers
                        .containsString("\"status\":\"OK\",\"copyright\":\"Copyright (c) 2021 The New York Times Company. All Rights Reserved.\",\"has_more\":true,\"num_results\":20,\"results\":[{\"display_title\":\"Malignant\",\"mpaa_rating\":\"R\",\"critics_pick\":0,\"byline\":\"Jeannette Catsoulis\",\"headline\":\"‘Malignant’ Review: Womb for Improvement\",\"summary_short\":\"James Wan’s horror throwback follows a young woman with a mysterious connection to a brutal killer.\",\"publication_date\":\"2021-09-10\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-10 17:13:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/10/movies/malignant-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Malignant\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/10/arts/10malignant-art/10malignant-art-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Come from Away\",\"mpaa_rating\":\"TV-14\",\"critics_pick\":0,\"byline\":\"Elisabeth Vincentelli\",\"headline\":\"‘Come From Away’ Review: Looking for Light in Somber Times\",\"summary_short\":\"The filmed version of this Broadway musical lands on Apple TV+ to deliver hope and kindness.\",\"publication_date\":\"2021-09-10\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-10 13:47:58\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/10/movies/come-from-away-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Come from Away\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/11/arts/11comefrom1/comefrom1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"The Card Counter\",\"mpaa_rating\":\"R\",\"critics_pick\":1,\"byline\":\"Manohla Dargis\",\"headline\":\"‘The Card Counter’ Review: A Gambler’s Existential Solitaire\",\"summary_short\":\"Oscar Isaac, Tiffany Haddish and Willem Dafoe star in the latest head trip from Paul Schrader, a story about betting on life.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 19:18:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/the-card-counter-review.html\",\"suggested_link_text\":\"Read the New York Times Review of The Card Counter\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/10/arts/00card-counter/00card-counter-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Azor\",\"mpaa_rating\":\"\",\"critics_pick\":1,\"byline\":\"Manohla Dargis\",\"headline\":\"‘Azor’ Review: A World on Fire, Discreetly\",\"summary_short\":\"In this low-key shocker set in Argentina in 1980, a Swiss banker travels through a world that he doesn’t seem to know is ablaze.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 16:51:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/azor-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Azor\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/10/arts/09azor-review/09azor-review-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Fire Music\",\"mpaa_rating\":\"\",\"critics_pick\":1,\"byline\":\"Glenn Kenny\",\"headline\":\"‘Fire Music’ Review: An Impassioned Case for Free Jazz\",\"summary_short\":\"The beautiful souls that created free jazz — including Ornette Coleman, Cecil Taylor, Don Cherry and Carla Bley — light up this new documentary from Tom Surgal.\",\"publication_date\":\"2021-09-09\",\"opening_date\":null,\"date_updated\":\"2021-09-09 12:42:21\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/fire-music-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Fire Music\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/10/arts/09fire-music-art/09fire-music-art-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Fauci\",\"mpaa_rating\":\"PG-13\",\"critics_pick\":0,\"byline\":\"Lisa Kennedy\",\"headline\":\"‘Fauci’ Review: The First Pandemic That Shaped the Good Doc\",\"summary_short\":\"This documentary underscores the hard-fought lessons the immunologist learned during the AIDS pandemic. Many shape his resolve now.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 17:18:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/fauci-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Fauci\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/08/arts/fauci1/fauci1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Language Lessons\",\"mpaa_rating\":\"\",\"critics_pick\":0,\"byline\":\"A.O. Scott\",\"headline\":\"‘Language Lessons’ Review: Who’s Zooming Who?\",\"summary_short\":\"Natalie Morales and Mark Duplass play a teacher and student in this found-footage story of a long-distance friendship.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 15:18:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/language-lessons-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Language Lessons\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/09/arts/09language1/09language1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Small Engine Repair\",\"mpaa_rating\":\"R\",\"critics_pick\":0,\"byline\":\"Maya Phillips\",\"headline\":\"‘Small Engine Repair’ Review: Of Mechanics and Men\",\"summary_short\":\"John Pollono directs and stars in an adaptation of his play that adds depth to the original text but also struggles in its translation from stage to screen.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 13:03:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/small-engine-repair-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Small Engine Repair\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/08/arts/small1/small1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Dogs\",\"mpaa_rating\":\"\",\"critics_pick\":0,\"byline\":\"Beatrice Loayza\",\"headline\":\"‘Dogs’ Review: Fish Out of Water\",\"summary_short\":\"A city boy inherits land used by the Mafia in this unoriginal neo-western crime thriller from Romania.\",\"publication_date\":\"2021-09-09\",\"opening_date\":null,\"date_updated\":\"2021-09-09 11:04:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/dogs-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Dogs\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/08/arts/dogs1/dogs1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"The Alpinist\",\"mpaa_rating\":\"PG-13\",\"critics_pick\":0,\"byline\":\"Ben Kenigsberg\",\"headline\":\"‘The Alpinist’ Review: Dizzying Heights\",\"summary_short\":\"This documentary tries to shed light on the attitude of a Canadian rock climber it describes as “elusive.”\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 11:03:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/the-alpinist-review.html\",\"suggested_link_text\":\"Read the New York Times Review of The Alpinist\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/08/arts/alpinist1/merlin_193976541_91b5e0ac-4b42-476c-95fa-e6a56546bcb4-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Kate\",\"mpaa_rating\":\"R\",\"critics_pick\":0,\"byline\":\"Teo Bugbee\",\"headline\":\"‘Kate’ Review: Lost in Assassination\",\"summary_short\":\"Mary Elizabeth Winstead plays a vengeful contract killer in this predictable thriller.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 11:05:02\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/kate-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Kate\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/09/arts/09kate-art/09kate-art-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"No Responders Left Behind\",\"mpaa_rating\":\"\",\"critics_pick\":0,\"byline\":\"Nicolas Rapold\",\"headline\":\"‘No Responders Left Behind’ Review: Heroes Need Heroes Too\",\"summary_short\":\"John Feal works tirelessly as an advocate for rescuers injured or sickened in the events of Sept. 11, 2001, and their aftermath.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-11\",\"date_updated\":\"2021-09-09 11:06:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/no-responders-left-behind-review.html\",\"suggested_link_text\":\"Read the New York Times Review of No Responders Left Behind\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/09/arts/09no-responders-pix/merlin_194014176_d89cae2f-1bdb-440a-93aa-35b6c3412208-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"The Capote Tapes\",\"mpaa_rating\":\"\",\"critics_pick\":0,\"byline\":\"Glenn Kenny\",\"headline\":\"‘The Capote Tapes’ Review: New Narratives and Unanswered Prayers\",\"summary_short\":\"This documentary adds some material to the tragic tale of a great American writer, but also teases at what it can’t deliver.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 11:01:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/the-capote-tapes-review.html\",\"suggested_link_text\":\"Read the New York Times Review of The Capote Tapes\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/07/arts/capotetapes1/capotetapes1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Queenpins\",\"mpaa_rating\":\"R\",\"critics_pick\":0,\"byline\":\"Jeannette Catsoulis\",\"headline\":\"‘Queenpins’ Review: Suburban Scammers\",\"summary_short\":\"Two cash-strapped neighbors devise a multimillion-dollar coupon swindle in this mildly entertaining comedy.\",\"publication_date\":\"2021-09-09\",\"opening_date\":\"2021-09-10\",\"date_updated\":\"2021-09-09 11:07:04\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/queenpins-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Queenpins\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/07/arts/queenpins1/queenpins1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Dating \\u0026 New York\",\"mpaa_rating\":\"PG-13\",\"critics_pick\":0,\"byline\":\"Ben Kenigsberg\",\"headline\":\"‘Dating \\u0026 New York’ Review: Texts and the City\",\"summary_short\":\"A winning cast helps sell a too-familiar premise about commitment-phobic millennials.\",\"publication_date\":\"2021-09-09\",\"opening_date\":null,\"date_updated\":\"2021-09-09 11:02:02\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/09/movies/dating-and-new-york-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Dating \\u0026 New York\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/07/arts/dating1/dating1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Cinderella\",\"mpaa_rating\":\"PG\",\"critics_pick\":0,\"byline\":\"Natalia Winkelman\",\"headline\":\"‘Cinderella’ Review: A Girlboss in Glass Slippers\",\"summary_short\":\"Camila Cabello stars in this musical reimagining of the fairy tale, which centers on an orphaned maiden who yearns not for true love but for corporate success as a dressmaker.\",\"publication_date\":\"2021-09-03\",\"opening_date\":\"2021-09-03\",\"date_updated\":\"2021-09-03 11:01:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/03/movies/cinderella-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Cinderella\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/02/arts/cinderella1/cinderella1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Wild Indian\",\"mpaa_rating\":\"\",\"critics_pick\":1,\"byline\":\"Lisa Kennedy\",\"headline\":\"‘Wild Indian’ Review: Reckoning With the Past to Save the Present\",\"summary_short\":\"This drama from Lyle Mitchell Corbine Jr. captures the various wounds of individual, familial and generational trauma.\",\"publication_date\":\"2021-09-02\",\"opening_date\":\"2021-09-03\",\"date_updated\":\"2021-09-02 12:20:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/02/movies/wild-indian-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Wild Indian\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/01/arts/wildindian1/wildindian1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Worth\",\"mpaa_rating\":\"PG-13\",\"critics_pick\":1,\"byline\":\"Ben Kenigsberg\",\"headline\":\"‘Worth’ Review: Appraising Lives\",\"summary_short\":\"Starring Michael Keaton, this is a surprisingly effective movie about a tricky subject — the creation of the Sept. 11 Victim Compensation Fund.\",\"publication_date\":\"2021-09-02\",\"opening_date\":\"2021-09-03\",\"date_updated\":\"2021-09-02 11:08:02\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/02/movies/worth-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Worth\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/01/arts/worth1/worth1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Faya Dayi\",\"mpaa_rating\":\"\",\"critics_pick\":1,\"byline\":\"Nicolas Rapold\",\"headline\":\"‘Faya Dayi’ Review: A Dream State\",\"summary_short\":\"Jessica Beshir’s debut feature settles into a trance-like flow.\",\"publication_date\":\"2021-09-02\",\"opening_date\":\"2021-09-03\",\"date_updated\":\"2021-09-02 11:04:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/02/movies/faya-dayi-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Faya Dayi\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/03/arts/faya1/faya1-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210}},{\"display_title\":\"Anne at 13,000 Ft.\",\"mpaa_rating\":\"\",\"critics_pick\":0,\"byline\":\"Manohla Dargis\",\"headline\":\"‘Anne at 13,000 Ft’ Review: A Woman Adrift\",\"summary_short\":\"A young day-care employee struggles to live and love in a world that, much like her, remains a blur.\",\"publication_date\":\"2021-09-02\",\"opening_date\":\"2021-09-03\",\"date_updated\":\"2021-09-02 17:37:03\",\"link\":{\"type\":\"article\",\"url\":\"https://www.nytimes.com/2021/09/02/movies/anne-at-13000-ft-review.html\",\"suggested_link_text\":\"Read the New York Times Review of Anne at 13,000 Ft.\"},\"multimedia\":{\"type\":\"mediumThreeByTwo210\",\"src\":\"https://static01.nyt.com/images/2021/09/03/arts/02anne-at/merlin_193813581_9d6b0bf5-4e24-43b8-a688-cd4c1f739a9a-mediumThreeByTwo440.jpg\",\"height\":140,\"width\":210"));
    }*/

}
