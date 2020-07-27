package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() throws Exception {
        app.goTo().groupPage();

        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("testGroupnull3");

        app.group().create(group);

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        group.withID(after.stream().mapToInt((g) -> g.getID()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
