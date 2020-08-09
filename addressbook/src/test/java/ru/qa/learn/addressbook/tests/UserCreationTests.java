package ru.qa.learn.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.io.Files.getFileExtension;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsers() throws IOException {
        List<Object[]> fin = new ArrayList<Object[]>();
        File file = new File("src/test/resources/users.json");
        String format = getFileExtension(file.getAbsolutePath());
        if (format.equals("CSV") || format.equals("csv")) {
            List<Object[]> list = new ArrayList<Object[]>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] split = line.split(";");
                    list.add(new Object[]{new UserData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
                            .withHomePhoneNumber(split[3]).withMobilePhoneNumber(split[4]).withWorkPhoneNumber(split[5])
                            .withEmail1(split[6]).withEmail2(split[7]).withEmail3(split[8])});
                    line = reader.readLine();
                }
                fin = list;
            }
        }else if (format.equals("XML") || format.equals("xml")) {
            try (BufferedReader reader = new BufferedReader (new FileReader(file))) {
                String xml = "";
                String line = reader.readLine();
                while (line != null) {
                    xml += line;
                    line = reader.readLine();
                }
                XStream xStream = new XStream();
                xStream.processAnnotations(UserData.class);
                List<UserData> users = (List<UserData>) xStream.fromXML(xml);
                fin = users.stream().map((g) -> new Object[]{g}).collect(Collectors.toList());
            }
        }else if (format.equals("JSON") || format.equals("json")) {
            try (BufferedReader reader = new BufferedReader (new FileReader(file))) {
                String json = "";
                String line = reader.readLine();
                while (line != null) {
                    json += line;
                    line = reader.readLine();
                }
                Gson gson = new Gson();
                List<UserData> users = gson.fromJson(json, new TypeToken<List<UserData>>() {
                }.getType());
                fin = users.stream().map((g) -> new Object[]{g}).collect(Collectors.toList());
            }
        }
        return fin.iterator();
    }

    @Test(dataProvider = "validUsers")
    public void testCreationUser(UserData user) {
        app.goTo().homePage();

        Users before = app.db().users();
        File photo = new File("src/test/resources/test1.jpeg");

        app.user().create(user, true);

        assertEquals(app.user().getUserCount(), before.size() + 1);
        Users after = app.db().users();
        assertThat(after, equalTo
                (before.withAdded(user.withID((after.stream().mapToInt((u) -> u.getID()).max().getAsInt())))));
        verifyUserListInUI();

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
