package ru.qa.learn.addressbook;

import org.testng.annotations.Test;

public class DelitionGroupTests extends TestBase {

  @Test
  public void testDelitionGroup() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
