package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() throws Exception {
        app.goTo().groupPage();

        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("testGroupnull3");

        app.group().create(group);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(group);
        Comparator<? super GroupData> byID = ((o1, o2) -> Integer.compare(o1.getID(), o2.getID()));
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }

}
