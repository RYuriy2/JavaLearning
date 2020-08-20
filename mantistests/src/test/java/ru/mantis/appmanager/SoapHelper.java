package ru.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.mantis.model.Issue;
import ru.mantis.model.Project;
import ru.mantis.model.Resolution;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.login")
                ,app.getProperty("web.password"));
        return Arrays.asList(projects).stream()
                .map((p)->new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL(app.getProperty("soap.url")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(app.getProperty("web.login")
                , app.getProperty("web.password")
                , BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getId()),issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueID = mc.mc_issue_add(app.getProperty("web.login"), app.getProperty("web.password"), issueData);
        IssueData createdIssueData = mc.mc_issue_get(app.getProperty("web.login"), app.getProperty("web.password"), issueID);
        return new Issue()
                .withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription())
                .withProject(new Project()
                        .withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));
    }


    public Resolution issueResolution(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ObjectRef issueResolution = mc.mc_issue_get(app.getProperty("web.login"),
                app.getProperty("web.password"),
                BigInteger.valueOf(issueId)).getResolution();
        return  new Resolution().withtId(issueResolution.getId().intValue()).withName(issueResolution.getName());
    }
}
