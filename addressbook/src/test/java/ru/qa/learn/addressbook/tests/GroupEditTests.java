package ru.qa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecinditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testnew"));
        }
    }

    @Test
    public void testEditGroup() throws Exception {
        Groups before = app.db().groups();
        GroupData editGroup = before.iterator().next();
        GroupData group = new GroupData().withID(editGroup.getID()).withName("testGroupEdit1last")
                .withHeader("testHeader1Edit1last").withFooter("testFooter1Edit1last");

        app.goTo().groupPage();

        app.group().edit(group);

        assertEquals(app.group().getGroupCount(), before.size());
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(editGroup).withAdded(group)));
    }

}
