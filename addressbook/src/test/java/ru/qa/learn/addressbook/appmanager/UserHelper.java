package ru.qa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.qa.learn.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase {

    public int getUserCount() {
        return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
    }

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void selectUser(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitCreateNewUser() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillUserData(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("address"), userData.getAddress());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("mobile"), userData.getPhoneNumber());
        type(By.name("email"), userData.getEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    public void initCreationNewUser() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedUser() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initEditUser(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitEditUser() {
        click(By.xpath("//input[@name='update']"));
    }

    public void createUser(UserData user, boolean creation) {
        initCreationNewUser();
        fillUserData(user, creation);
        submitCreateNewUser();
        returnToHomePage();
    }

    public boolean isThereUser() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public List<UserData> getUserList() {
        List<UserData> users = new ArrayList<UserData>();
        List<WebElement> line = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr"));
        for (WebElement element : line) {
            List<WebElement> elements = element.findElements(By.tagName("td"));
            if (elements.size() != 0) {
                String firstname = elements.get(2).getText();
                String lastname = elements.get(1).getText();
                String email = elements.get(4).getText();
                String phoneNumber = elements.get(5).getText();
                String address = elements.get(3).getText();
                int id = Integer.parseInt(elements.get(0).findElement(By.name("selected[]")).getAttribute("id"));
                UserData user = new UserData(id, firstname, address, lastname,
                        phoneNumber, email, null);
                users.add(user);
            }
        }
        return users;
    }
}
