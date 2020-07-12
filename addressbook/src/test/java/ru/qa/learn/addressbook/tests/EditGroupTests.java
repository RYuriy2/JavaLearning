package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

public class EditGroupTests extends TestBase {

  @Test
  public void testEditGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initEditGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroupEdit1", "testHeader1Edit1", "testFooter1Edit1"));
    app.getGroupHelper().submitEditGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
