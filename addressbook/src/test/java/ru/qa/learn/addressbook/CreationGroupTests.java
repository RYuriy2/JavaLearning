package ru.qa.learn.addressbook;

import org.testng.annotations.Test;

public class CreationGroupTests extends TestBase{

  @Test
  public void testCreationGroup() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("testGroup", "testHeader1", "testFooter1"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
