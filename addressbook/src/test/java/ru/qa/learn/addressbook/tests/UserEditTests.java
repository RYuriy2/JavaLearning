package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;

public class UserEditTests extends TestBase {

  @Test
  public void testEditUser(){
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereUser()){
      app.getUserHelper().createUser(new UserData("Мефодий",
              "Михайлович", "Буслаев", "+79009009090",
              "test@test.com","testGroupnull"),true);
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initEditUser(before.size()-1);
    UserData user = new UserData(before.get(before.size() - 1).getID(),"МихаилNew",
            "МихайловичNew", "БуслаевNew", "+79009009099",
            "testNew@test.com", null);
    app.getUserHelper().fillUserData(user,false);
    app.getUserHelper().submitEditUser();
    app.getUserHelper().returnToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(),before.size());
    before.remove(before.size()-1);
    before.add(user);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
