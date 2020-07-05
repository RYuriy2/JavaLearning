package ru.qa.learn.addressbook;

import org.testng.annotations.Test;

public class DelitionUserTests extends TestBase {

  @Test
  public void testDelitionUser() throws Exception {
    gotoHomePage();
    selectUser();
    deleteSelectedUser();
    closeAlertPopUp();
    gotoHomePage();
  }

}
