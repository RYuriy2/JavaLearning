package ru.qa.restbugify.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.qa.restbugify.appmanager.ApplicationManager;
import ru.qa.restbugify.model.Issue;
//import ru.mantis.model.Resolution;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestBase {


    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        Set<Issue> issues = app.rest().getIssuesById(issueId);                                                             //Получаем список issue c заданным ID
        Issue issue = issues.iterator().next();                                                                //Т.к. ID является уникальным, то в полученном выше списке всего 1 элемент, его и берём для дальнейшей обработки
        return issue.getState_name().equals("Open")
                || issue.getState_name().equals("In Progress")
                || issue.getState_name().equals("Reopened");                         //Проверяем значение поля State_name в отктытых статусах возвращает true иначе false
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
