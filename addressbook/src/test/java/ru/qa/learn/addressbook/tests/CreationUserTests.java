package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

public class CreationUserTests extends TestBase {

  @Test
  public void testCreationUser() throws Exception {
    app.getUserHelper().initCreationNewUser();
    app.getUserHelper().fillUserData(new UserData("Михаил", "Михайлович", "Буслаев", "+79009009090", "test@test.com"));
    app.getUserHelper().submitCreateNewUser();
    app.getUserHelper().returnToHomePage();
  }
}
