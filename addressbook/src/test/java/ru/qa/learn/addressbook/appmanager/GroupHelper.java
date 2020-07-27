package ru.qa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qa.learn.addressbook.model.GroupData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }


    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }


    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }


    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectGroupByID(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void initEditGroup() {
        click(By.name("edit"));
    }

    public void edit(GroupData group) {
        selectGroupByID(group.getID());
        initEditGroup();
        fillGroupForm(group);
        submitEditGroup();
        returnToGroupPage();
    }

    public void submitEditGroup() {
        click(By.name("update"));
    }


    public void delete(GroupData group) {
        selectGroupByID(group.getID());
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void deleteSelectedGroups() {
        click(By.xpath("(//input[@name='delete'])"));
    }


    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }


    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }


    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withID(id).withName(name));
        }
        return groups;
    }


}
