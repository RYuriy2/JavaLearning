package ru.qa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.qa.learn.addressbook.model.UserData;

public class UserHelper {
    private WebDriver wd;

    public UserHelper(WebDriver wd) {
        this.wd=wd;
    }

    public void selectUser() {
        wd.findElement(By.name("selected[]")).click();
    }

    public void returnToHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void submitCreateNewUser() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillUserData(UserData userData) {
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(userData.getFirstname());
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(userData.getMidlname());
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(userData.getLastname());
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(userData.getPhoneNumber());
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(userData.getEmail());
        }

    public void initCreationNewUser() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void deleteSelectedUser() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }
}
