package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import java.util.List;

public class UserDelitionTests extends TestBase {

  @Test
  public void testDelitionUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereUser()){
      app.getUserHelper().createUser(new UserData("Мефодий", "Михайлович",
              "Буслаев", "+79009009090", "test@test.com",
              "testGroupnull"),true);
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().selectUser();
    app.getUserHelper().deleteSelectedUser();
    app.closeAlertPopUp();
    app.getNavigationHelper().gotoHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(),before.size() - 1);
  }

}
