package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

public class CreationGroupTests extends TestBase {

  @Test
  public void testCreationGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("testGroupnull", null, null));
  }

}
