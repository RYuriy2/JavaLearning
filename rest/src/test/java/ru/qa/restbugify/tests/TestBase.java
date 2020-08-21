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
        Set<Issue> issues = app.rest().getIssues();                                                             //Получаем список issue
        List<Issue> collect = issues.stream().filter(i -> i.getId() == issueId).collect(Collectors.toList());   //Выбираем из списка в отдельный список тот issue, у которого ID соответсвует заданному
        Issue issue = collect.iterator().next();                                                                //Т.к. ID является уникальным, то в полученном выше списке всего 1 элемент, его и берём для дальнейшей обработки
        return issue.getState() == 0 || issue.getState() == 1 || issue.getState() == 4;                         //Проверяем значение поля State (статус 0 - open, 1 - in dev, 4 - reopend) в отктытых статусах возвращает true иначе false
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
