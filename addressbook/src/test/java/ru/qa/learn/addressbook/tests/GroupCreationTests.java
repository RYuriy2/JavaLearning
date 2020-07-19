package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("testGroupnull", null, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (GroupData g : after) {
            if (g.getID() > max) {
                max = g.getID();
            }
        }
        group.setID(max);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
