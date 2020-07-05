package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

public class CreationGroupTests extends TestBase {

  @Test
  public void testCreationGroup() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("testGroup", "testHeader1", "testFooter1"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
