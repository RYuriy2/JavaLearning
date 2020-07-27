package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import java.util.List;

public class UserDelitionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.user().list().size() == 0) {
            app.user().create(new UserData("Мефодий",
                    "Михайлович", "Буслаев", "+79009009090",
                    "test@test.com", "testGroupnull"), true);
        }
    }

    @Test(enabled = true)
    public void testDelitionUser() {
        List<UserData> before = app.user().list();
        int index = before.size() - 1;

        app.user().delete(index);

        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
