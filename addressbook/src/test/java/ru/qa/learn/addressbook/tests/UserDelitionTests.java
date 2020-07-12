package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

public class UserDelitionTests extends TestBase {

  @Test
  public void testDelitionUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereUser()){
      app.getUserHelper().createUser(new UserData("Мефодий", "Михайлович", "Буслаев", "+79009009090", "test@test.com","testGroupEdit1"),true);
    }
    app.getUserHelper().selectUser();
    app.getUserHelper().deleteSelectedUser();
    app.closeAlertPopUp();
    app.getNavigationHelper().gotoHomePage();
  }

}
