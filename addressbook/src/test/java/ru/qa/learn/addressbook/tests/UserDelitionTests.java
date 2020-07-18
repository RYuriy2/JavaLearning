package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

public class UserDelitionTests extends TestBase {

  @Test
  public void testDelitionUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereUser()){
      app.getUserHelper().createUser(new UserData("Мефодий", "Михайлович",
              "Буслаев", "+79009009090", "test@test.com",
              "testGroupnull"),true);
    }
    int before = app.getUserHelper().getUserCount();
    app.getUserHelper().selectUser();
    app.getUserHelper().deleteSelectedUser();
    app.closeAlertPopUp();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before - 1);
  }

}
