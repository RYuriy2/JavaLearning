package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;

public class UserCreationTests extends TestBase {

    @Test
    public void testCreationUser() {
        app.getNavigationHelper().gotoHomePage();
        List<UserData> before = app.getUserHelper().getUserList();
        UserData user = new UserData("Михаил",
                "Михайлович", "Буслаев", "+79009009090",
                "test@test.com", "testGroupnull");
        app.getUserHelper().createUser(user, true);
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() + 1);
        user.setID(after.stream().max(((o1, o2) -> Integer.compare(o1.getID(),o2.getID()))).get().getID());
        before.add(user);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
