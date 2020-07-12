package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

public class DelitionGroupTests extends TestBase {

  @Test
  public void testDelitionGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("testnew",null,null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
