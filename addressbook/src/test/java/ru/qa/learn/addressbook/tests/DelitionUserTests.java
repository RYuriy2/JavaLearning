package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;

public class DelitionUserTests extends TestBase {

  @Test
  public void testDelitionUser() throws Exception {
    app.gotoHomePage();
    app.selectUser();
    app.deleteSelectedUser();
    app.closeAlertPopUp();
    app.gotoHomePage();
  }

}
