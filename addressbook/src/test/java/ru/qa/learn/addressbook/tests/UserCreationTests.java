package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testCreationUser() throws Exception {
    app.getUserHelper().createUser(new UserData("Михаил",
            "Михайлович", "Буслаев", "+79009009090",
            "test@test.com","testGroupEdit1"),true);
  }
}
