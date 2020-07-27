package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;
import java.util.Set;

public class UserCreationTests extends TestBase {

    @Test(enabled = true)
    public void testCreationUser() {
        app.goTo().homePage();

        Set<UserData> before = app.user().all();
        UserData user = new UserData().withLastname("Михаил").withAddress("Михайлович").withFirstname("Буслаев")
        .withPhoneNumber("+79009009090").withEmail("test@test.com").withGroup("testGroupnull");

        app.user().create(user, true);

        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        user.withID((after.stream().mapToInt((u) -> u.getID()).max().getAsInt()));
        before.add(user);
        Assert.assertEquals(before, after);

    }
}
