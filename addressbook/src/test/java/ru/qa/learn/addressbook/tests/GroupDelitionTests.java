package ru.qa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();

        app.group().delete(deletedGroup);

        Groups after = app.group().all();
        assertEquals(after.size(), before.size() - 1);
        before.remove(deletedGroup);
        assertThat(after, equalTo(before.withOut(deletedGroup)));
    }

}
