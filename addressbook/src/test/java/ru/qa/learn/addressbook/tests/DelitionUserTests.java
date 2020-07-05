package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;

public class DelitionUserTests extends TestBase {

  @Test
  public void testDelitionUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getUserHelper().selectUser();
    app.getUserHelper().deleteSelectedUser();
    app.closeAlertPopUp();
    app.getNavigationHelper().gotoHomePage();
  }

}
