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

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void initCreationNewUser() {
        click(By.linkText("add new"));
    }

    public void create(UserData user, boolean creation) {
        initCreationNewUser();
        fillUserData(user, creation);
        submitCreateNewUser();
        returnToHomePage();
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


    public void selectUser(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }


    public void initEditUser(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void edit(UserData user, int index) {
        initEditUser(index);
        fillUserData(user, false);
        submitEditUser();
        returnToHomePage();
    }

    public void submitEditUser() {
        click(By.xpath("//input[@name='update']"));
    }


    public void delete(int index) {
        selectUser(index);
        deleteSelectedUser();
        closeAlertPopUp();
        returnToHomePage();
    }

    public void deleteSelectedUser() {
        click(By.xpath("//input[@value='Delete']"));
    }


    public void returnToHomePage() {
        click(By.linkText("home"));
    }


    public boolean isThereUser() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }


    public List<UserData> list() {
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
                users.add(new UserData().withID(id).withFirstname(firstname).withAddress(address).withLastname(lastname)
                        .withPhoneNumber(phoneNumber).withEmail(email));
            }
        }
        return users;
    }


    public int getUserCount() {
        return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
    }


    public void closeAlertPopUp() {
        wd.switchTo().alert().accept();
    }
}
