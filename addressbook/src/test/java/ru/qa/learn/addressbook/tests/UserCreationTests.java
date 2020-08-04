package ru.qa.learn.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsers() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader (new FileReader(new File("src/test/resources/users.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new UserData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
                    .withHomePhoneNumber(split[3]).withMobilePhoneNumber(split[4]).withWorkPhoneNumber(split[5])
                    .withEmail1(split[6]).withEmail2(split[7]).withEmail3(split[8])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test(enabled = true,dataProvider = "validUsers")
    public void testCreationUser(UserData user) {
        app.goTo().homePage();

        Users before = app.user().all();
        File photo = new File("src/test/resources/test1.jpeg");

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
