package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

public class CreationUserTests extends TestBase {

  @Test
  public void testCreationUser() throws Exception {
    app.initCreationNewUser();
    app.fillUserData(new UserData("Михаил", "Михайлович", "Буслаев", "+79009009090", "test@test.com"));
    app.submitCreateNewUser();
    app.returnToHomePage();
  }
}
