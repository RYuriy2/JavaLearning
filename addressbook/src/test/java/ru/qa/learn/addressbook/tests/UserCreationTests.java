package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserCreationTests extends TestBase {

    @Test(enabled = true)
    public void testCreationUser() {
        app.goTo().homePage();

        Users before = app.user().all();
        UserData user = new UserData().withLastname("Михаил").withAddress("Михайлович").withFirstname("Буслаев")
        .withPhoneNumber("+79009009090").withEmail("test@test.com").withGroup("testGroupnull");

        app.user().create(user, true);

        assertEquals(app.user().getUserCount(), before.size() + 1);
        Users after = app.user().all();
        assertThat(after, equalTo
                (before.withAdded(user.withID((after.stream().mapToInt((u) -> u.getID()).max().getAsInt())))));

    }
}
