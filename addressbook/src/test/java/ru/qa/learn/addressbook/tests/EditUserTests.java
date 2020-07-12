package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

public class EditUserTests extends TestBase {

  @Test
  public void testEditUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getUserHelper().initEditUser();
    app.getUserHelper().fillUserData(new UserData("МихаилNew", "МихайловичNew", "БуслаевNew", "+79009009099", "testNew@test.com", null),false);
    app.getUserHelper().submitEditUser();
    app.getUserHelper().returnToHomePage();
  }
}
