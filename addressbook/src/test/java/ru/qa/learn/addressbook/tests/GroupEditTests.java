package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

import java.util.*;

public class GroupEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecinditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("testnew"));
        }
    }

    @Test
    public void testEditGroup() throws Exception {
        Set<GroupData> before = app.group().all();
        GroupData editGroup = before.iterator().next();
        GroupData group = new GroupData().withID(editGroup.getID()).withName("testGroupEdit1last")
                .withHeader("testHeader1Edit1last").withFooter("testFooter1Edit1last");

        app.group().edit(group);

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(editGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
