package ru.qa.learn.addressbook;

import org.testng.annotations.Test;

public class CreationUserTests extends TestBase {

  @Test
  public void testCreationUser() throws Exception {
    initCreationNewUser();
    fillUserData(new UserData("Михаил", "Михайлович", "Буслаев", "+79009009090", "test@test.com"));
    submitCreateNewUser();
    returnToHomePage();
  }
}
