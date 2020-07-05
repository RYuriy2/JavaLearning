package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;

public class DelitionGroupTests extends TestBase {

  @Test
  public void testDelitionGroup() throws Exception {
    app.gotoGroupPage();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.returnToGroupPage();
  }

}
