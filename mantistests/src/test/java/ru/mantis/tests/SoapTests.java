package ru.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.mantis.model.Issue;
import ru.mantis.model.Project;
import ru.mantis.model.Resolution;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test(enabled = true)
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        int issueId = 1;
        skipIfNotFixed(issueId);
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects){
            System.out.println(project.getName());
        }
    }

    @Test(enabled = false)
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Project project = projects.iterator().next();
        Issue issue = new Issue()
                .withSummary("Test summary")
                .withDescription("Test issue description")
                .withProject(project);
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(),created.getSummary());
    }

    @Test (enabled = false)
    public void testIssueResolution() throws RemoteException, ServiceException, MalformedURLException {
        int issueId = 1;
        Resolution resol = app.soap().issueResolution(issueId);
        System.out.println(resol.getId());
        System.out.println(resol.getName());
    }
}
