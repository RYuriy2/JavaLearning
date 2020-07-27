package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;
import java.util.Set;

public class UserEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().withLastname("Михаил").withAddress("Михайлович").withFirstname("Буслаев")
                    .withPhoneNumber("+79009009090").withEmail("test@test.com").withGroup("testGroupnull"), true);
        }
    }

    @Test(enabled = true)
    public void testEditUser() {
        Set<UserData> before = app.user().all();
        UserData editUser = before.iterator().next();
        UserData user = new UserData().withID(editUser.getID()).withLastname("МихаилNew")
                .withAddress("МихайловичNew").withFirstname("БуслаевNew").withPhoneNumber("+79009009099")
                .withEmail("testNew@test.com");

        app.user().edit(user);

        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(editUser);
        before.add(user);
        Assert.assertEquals(before, after);
    }

}
