package ru.qa.learn.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DelitionUserTests extends TestBase {

  @Test
  public void testDelitionUser() throws Exception {
    gotoHomePage();
    selectUser();
    deleteSelectedUser();
    closeAlertPopUp();
    gotoHomePage();
  }

}
