package ru.qa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public void fillUserData(UserData userData) {
        type(By.name("firstname"),userData.getFirstname());
        type(By.name("middlename"),userData.getMidlname());
        type(By.name("lastname"),userData.getLastname());
        type(By.name("mobile"),userData.getPhoneNumber());
        type(By.name("email"),userData.getEmail());
    }

    public void initCreationNewUser() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedUser() {
        click(By.xpath("//input[@value='Delete']"));
    }
}
