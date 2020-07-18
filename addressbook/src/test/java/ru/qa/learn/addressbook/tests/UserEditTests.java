package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

public class UserEditTests extends TestBase {

  @Test
  public void testEditUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereUser()){
      app.getUserHelper().createUser(new UserData("Мефодий",
              "Михайлович", "Буслаев", "+79009009090",
              "test@test.com","testGroupnull"),true);
    }
    int before = app.getUserHelper().getUserCount();
    app.getUserHelper().initEditUser();
    app.getUserHelper().fillUserData(new UserData("МихаилNew",
            "МихайловичNew", "БуслаевNew", "+79009009099",
            "testNew@test.com", null),false);
    app.getUserHelper().submitEditUser();
    app.getUserHelper().returnToHomePage();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before);
  }
}
