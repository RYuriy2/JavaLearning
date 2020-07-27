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
            app.user().create(new UserData().withLastname("Михаил").withAddress("Михайлович").withFirstname("Буслаев")
                    .withPhoneNumber("+79009009090").withEmail("test@test.com").withGroup("testGroupnull"), true);
        }
    }

    @Test(enabled = true)
    public void testEditUser() {
        List<UserData> before = app.user().list();
        UserData user = new UserData().withID(before.get(before.size() - 1).getID()).withLastname("МихаилNew")
                .withAddress("МихайловичNew").withFirstname("БуслаевNew").withPhoneNumber("+79009009099")
                .withEmail("testNew@test.com");
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
