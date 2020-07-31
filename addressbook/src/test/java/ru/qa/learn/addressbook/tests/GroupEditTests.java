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
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("testnew"));
        }
    }

    @Test
    public void testEditGroup() throws Exception {
        Groups before = app.group().all();
        GroupData editGroup = before.iterator().next();
        GroupData group = new GroupData().withID(editGroup.getID()).withName("testGroupEdit1last")
                .withHeader("testHeader1Edit1last").withFooter("testFooter1Edit1last");

        app.group().edit(group);

        assertEquals(app.group().getGroupCount(), before.size());
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withOut(editGroup).withAdded(group)));
    }

}
