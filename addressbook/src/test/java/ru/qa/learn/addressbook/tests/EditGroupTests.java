package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

public class EditGroupTests extends TestBase {

  @Test
  public void testEditGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initEditGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroupEdit", "testHeader1Edit", "testFooter1Edit"));
    app.getGroupHelper().submitEditGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
