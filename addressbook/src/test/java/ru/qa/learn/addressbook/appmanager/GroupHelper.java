package ru.qa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;

import java.util.List;

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
        groupCache = null;
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
        groupCache = null;
        returnToGroupPage();
    }

    public void submitEditGroup() {
        click(By.name("update"));
    }


    public void delete(GroupData group) {
        selectGroupByID(group.getID());
        deleteSelectedGroups();
        groupCache = null;
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

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        } else {
            groupCache = new Groups();
            List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
            for (WebElement element : elements) {
                String name = element.getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                groupCache.add(new GroupData().withID(id).withName(name));
            }
            return new Groups(groupCache);
        }
    }


}
