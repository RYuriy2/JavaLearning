package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import java.util.List;

public class UserEditTests extends TestBase {

  @Test
  public void testEditUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereUser()){
      app.getUserHelper().createUser(new UserData("Мефодий",
              "Михайлович", "Буслаев", "+79009009090",
              "test@test.com","testGroupnull"),true);
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initEditUser();
    app.getUserHelper().fillUserData(new UserData("МихаилNew",
            "МихайловичNew", "БуслаевNew", "+79009009099",
            "testNew@test.com", null),false);
    app.getUserHelper().submitEditUser();
    app.getUserHelper().returnToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(),before.size() + 1);
  }
}
