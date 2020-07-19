package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.UserData;

import java.util.Comparator;
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
        before.add(user);
        Comparator<? super UserData> byID = ((o1, o2) -> Integer.compare(o1.getID(),o2.getID()));
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before,after);

    }
}
