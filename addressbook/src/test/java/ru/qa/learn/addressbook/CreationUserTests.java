package ru.qa.learn.addressbook;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class CreationUserTests extends TestBase {

  @Test
  public void testCreationUser() throws Exception {
    initCreationNewUser();
    fillUserData(new UserData("Михаил", "Михайлович", "Буслаев", "+79009009090", "test@test.com"));
    submitCreateNewUser();
    returnToHomePage();
  }
}
