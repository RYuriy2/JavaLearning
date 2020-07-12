package ru.qa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.qa.learn.addressbook.model.UserData;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void selectUser() {
        click(By.name("selected[]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitCreateNewUser() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillUserData(UserData userData, boolean creation) {
        type(By.name("firstname"),userData.getFirstname());
        type(By.name("middlename"),userData.getMidlname());
        type(By.name("lastname"),userData.getLastname());
        type(By.name("mobile"),userData.getPhoneNumber());
        type(By.name("email"),userData.getEmail());
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

    public void initEditUser(){
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitEditUser(){
        click(By.xpath("//input[@name='update']"));
    }

    public void createUser(UserData user, boolean creation) {
        initCreationNewUser();
        fillUserData(user,creation);
        submitCreateNewUser();
        returnToHomePage();
    }

    public boolean isThereUser() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }
}
