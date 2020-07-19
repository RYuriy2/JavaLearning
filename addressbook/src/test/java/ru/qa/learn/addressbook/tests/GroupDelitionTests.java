package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

import java.util.List;

public class GroupDelitionTests extends TestBase {

    @Test
    public void testDelitionGroup() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createGroup(new GroupData("testnew", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
