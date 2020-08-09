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
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testnew"));
        }
    }

    @Test
    public void testDelitionGroup() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();

        app.goTo().groupPage();

        app.group().delete(deletedGroup);

        assertEquals(app.group().getGroupCount(), before.size() - 1);
        before.remove(deletedGroup);
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(deletedGroup)));
        verifyGroupListInUI();
    }

}
