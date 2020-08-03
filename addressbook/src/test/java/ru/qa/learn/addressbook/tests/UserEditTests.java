package ru.qa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().withLastname("Михаил").withAddress("Михайлович").withFirstname("Буслаев")
                    .withHomePhoneNumber("+79009009090").withEmail("test@test.com").withGroup("testGroupnull"), true);
        }
    }

    @Test(enabled = true)
    public void testEditUser() {
        Users before = app.user().all();
        UserData editUser = before.iterator().next();
        UserData user = new UserData().withID(editUser.getID()).withLastname("МихаилNew")
                .withAddress("МихайловичNew").withFirstname("БуслаевNew").withHomePhoneNumber("+79009009099")
                .withEmail("testNew@test.com");

        app.user().edit(user);

        assertEquals(app.user().getUserCount(), before.size());
        Users after = app.user().all();
        assertThat(after, equalTo(before.without(editUser).withAdded(user)));
    }

}
