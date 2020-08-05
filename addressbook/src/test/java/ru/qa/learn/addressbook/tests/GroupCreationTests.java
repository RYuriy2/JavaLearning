package ru.qa.learn.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.Files;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.io.Files.getFileExtension;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> fin = new ArrayList<Object[]>();
        File file = new File("src/test/resources/groups.json");
        String format = getFileExtension(file.getAbsolutePath());
        if (format.equals("CSV") || format.equals("csv")) {
            List<Object[]> list = new ArrayList<Object[]>();
            try (BufferedReader reader = new BufferedReader (new FileReader(file))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] split = line.split(";");
                    list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
                    line = reader.readLine();
                }
                fin = list;
            }
        } else if (format.equals("XML") || format.equals("xml")) {
            try (BufferedReader reader = new BufferedReader (new FileReader(file))) {
                String xml = "";
                String line = reader.readLine();
                while (line != null) {
                    xml += line;
                    line = reader.readLine();
                }
                XStream xStream = new XStream();
                xStream.processAnnotations(GroupData.class);
                List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
                fin = groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList());
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
                List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
                }.getType());
                fin = groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList());
            }
        }
        return fin.iterator();
    }

    @Test (dataProvider = "validGroups")
    public void testCreationGroup(GroupData group) throws Exception {
        app.goTo().groupPage();

        Groups before = app.group().all();

        app.group().create(group);

        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo
                (before.withAdded(group.withID(after.stream().mapToInt((g) -> g.getID()).max().getAsInt()))));
    }

}
