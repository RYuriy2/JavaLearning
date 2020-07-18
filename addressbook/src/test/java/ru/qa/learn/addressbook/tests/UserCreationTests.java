package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.UserData;

import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testCreationUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().createUser(new UserData("Михаил",
            "Михайлович", "Буслаев", "+79009009090",
            "test@test.com","testGroupnull"),true);
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(),before.size() + 1);
  }
}
