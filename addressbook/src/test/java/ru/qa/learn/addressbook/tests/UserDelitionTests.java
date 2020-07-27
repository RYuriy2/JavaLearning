package ru.qa.learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import java.util.List;

public class UserDelitionTests extends TestBase {

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
    public void testDelitionUser() {
        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().selectUser(before.size() - 1);
        app.getUserHelper().deleteSelectedUser();
        app.closeAlertPopUp();
        app.getNavigationHelper().gotoHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
