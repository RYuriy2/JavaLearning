package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() throws Exception {
        app.goTo().groupPage();

        Groups before = app.group().all();
        GroupData group = new GroupData().withName("testGroupnull3");

        app.group().create(group);

        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo
                (before.withAdded(group.withID(after.stream().mapToInt((g) -> g.getID()).max().getAsInt()))));
    }

}
