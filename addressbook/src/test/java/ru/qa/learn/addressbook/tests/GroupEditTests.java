package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

import java.util.*;

public class GroupEditTests extends TestBase {

  @BeforeMethod
  public void ensurePrecinditions () {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("testnew",null,null));
    }
  }

  @Test
  public void testEditGroup() throws Exception {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData(before.get(before.size() - 1).getID(),"testGroupEdit1last",
            "testHeader1Edit1last", "testFooter1Edit1last");
    int index = before.size() - 1;
    app.getGroupHelper().editGroup(group, index);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = ((o1, o2) -> Integer.compare(o1.getID(),o2.getID()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

}
