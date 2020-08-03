package ru.qa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserDelitionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().withLastname("Михаил").withAddress("Михайлович").withFirstname("Буслаев")
                    .withHomePhoneNumber("+79009009090").withEmail1("test@test.com").withGroup("testGroupnull"), true);
        }
    }

    @Test(enabled = true)
    public void testDelitionUser() {
        Users before = app.user().all();
        UserData deletedUser = before.iterator().next();

        app.user().delete(deletedUser);

        assertEquals(app.user().getUserCount(), before.size() - 1);
        Users after = app.user().all();
        before.remove(deletedUser);
        assertThat(after, equalTo(before.without(deletedUser)));
    }

}
