package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.UserData;

import java.util.List;

public class GroupEditTests extends TestBase {

  @Test
  public void testEditGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("testnew",null,null));
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initEditGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroupEdit1last",
            "testHeader1Edit1last", "testFooter1Edit1last"));
    app.getGroupHelper().submitEditGroup();
    app.getGroupHelper().returnToGroupPage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(),before.size() + 1);
  }

}
