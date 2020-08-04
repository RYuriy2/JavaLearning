package ru.qa.learn.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader (new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
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
