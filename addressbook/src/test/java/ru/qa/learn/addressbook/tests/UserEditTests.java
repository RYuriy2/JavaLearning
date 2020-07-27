package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserEditTests extends TestBase {

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
    public void testEditUser() {
        List<UserData> before = app.user().list();
        UserData user = new UserData(before.get(before.size() - 1).getID(), "МихаилNew",
                "МихайловичNew", "БуслаевNew", "+79009009099",
                "testNew@test.com", null);
        int index = before.size() - 1;

        app.user().edit(user, index);

        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(user);
        Comparator<? super UserData> byID = ((o1, o2) -> Integer.compare(o1.getID(), o2.getID()));
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }

}
