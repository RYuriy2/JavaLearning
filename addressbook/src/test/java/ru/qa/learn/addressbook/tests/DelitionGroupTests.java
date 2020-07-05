package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;

public class DelitionGroupTests extends TestBase {

  @Test
  public void testDelitionGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
