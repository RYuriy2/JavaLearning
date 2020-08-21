package ru.qa.restbugify.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.restbugify.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(90);
        Set<Issue> oldIssues = app.rest().getIssuesById(90);
        Issue newIssue = new Issue().withSubject("Revin test REST").withDescription("New test issue");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(new Issue().withId(issueId));
        Assert.assertEquals(newIssues,oldIssues);
    }


}
