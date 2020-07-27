package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class UserEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.getNavigationHelper().gotoHomePage();
        if (!app.getUserHelper().isThereUser()) {
            app.getUserHelper().createUser(new UserData("Мефодий",
                    "Михайлович", "Буслаев", "+79009009090",
                    "test@test.com", "testGroupnull"), true);
        }
    }

    @Test (enabled = false)
    public void testEditUser() {

        List<UserData> before = app.getUserHelper().getUserList();
        UserData user = new UserData(before.get(before.size() - 1).getID(), "МихаилNew",
                "МихайловичNew", "БуслаевNew", "+79009009099",
                "testNew@test.com", null);
        int index = before.size() - 1;
        app.getUserHelper().editUser(user, index);
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(user);
        Comparator<? super UserData> byID = ((o1, o2) -> Integer.compare(o1.getID(),o2.getID()));
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before,after);
    }

}
