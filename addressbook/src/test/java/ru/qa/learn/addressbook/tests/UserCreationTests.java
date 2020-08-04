package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserCreationTests extends TestBase {

    @Test(enabled = true)
    public void testCreationUser() {
        app.goTo().homePage();

        Users before = app.user().all();
        File photo = new File("src/test/resources/test1.jpeg");
        UserData user = new UserData().withLastname("Михаил1").withAddress("Михайлович1").withFirstname("Буслаев1")
        .withMobilePhoneNumber("+79009009090").withEmail1("test@test.com").withGroup("testGroupnull").withPhoto(photo);

        app.user().create(user, true);

//        assertEquals(app.user().getUserCount(), before.size() + 1);
        Users after = app.user().all();
        assertThat(after, equalTo
                (before.withAdded(user.withID((after.stream().mapToInt((u) -> u.getID()).max().getAsInt())))));

    }

    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File (".");
        System.out.println(currentDir.getAbsoluteFile());
        File photo = new File("src/test/resources/test1.jpeg");
        System.out.println(photo.getAbsoluteFile());
        System.out.println(photo.exists());
    }
}
