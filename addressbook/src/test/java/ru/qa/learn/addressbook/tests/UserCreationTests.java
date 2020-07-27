package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

    @Test(enabled = true)
    public void testCreationUser() {
        app.goTo().homePage();

        List<UserData> before = app.user().list();
        UserData user = new UserData().withLastname("Михаил").withAddress("Михайлович").withFirstname("Буслаев")
        .withPhoneNumber("+79009009090").withEmail("test@test.com").withGroup("testGroupnull");

        app.user().create(user, true);

        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(user);
        Comparator<? super UserData> byID = ((o1, o2) -> Integer.compare(o1.getID(), o2.getID()));
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);

    }
}
