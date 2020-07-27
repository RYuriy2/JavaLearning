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
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("testnew"));
        }
    }

    @Test
    public void testEditGroup() throws Exception {
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withID(before.get(before.size() - 1).getID()).withName("testGroupEdit1last")
                .withHeader("testHeader1Edit1last").withFooter("testFooter1Edit1last");
        int index = before.size() - 1;

        app.group().edit(group, index);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byID = ((o1, o2) -> Integer.compare(o1.getID(), o2.getID()));
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }

}
