package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import java.util.Set;

public class GroupDelitionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecinditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("testnew"));
        }
    }

    @Test
    public void testDelitionGroup() {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();

        app.group().delete(deletedGroup);

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }

}
