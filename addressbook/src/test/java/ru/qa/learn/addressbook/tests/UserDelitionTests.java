package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;
import java.util.Set;

public class UserDelitionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().withLastname("Михаил").withAddress("Михайлович").withFirstname("Буслаев")
                    .withPhoneNumber("+79009009090").withEmail("test@test.com").withGroup("testGroupnull"), true);
        }
    }

    @Test(enabled = true)
    public void testDelitionUser() {
        Set<UserData> before = app.user().all();
        UserData deleterUser = before.iterator().next();

        app.user().delete(deleterUser);

        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(deleterUser);
        Assert.assertEquals(before, after);
    }

}
