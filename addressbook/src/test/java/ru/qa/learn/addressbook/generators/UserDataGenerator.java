package ru.qa.learn.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.tests.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator extends TestBase {

    @Parameter(names = "-c", description = "GroupsCount")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;
    private SessionFactory sessionFactory;

    public static void main(String[] args) throws IOException {
        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        if (format.equals("CSV") || format.equals("csv")) {
            saveAsCSV(users, new File(file));
        } else if (format.equals("XML") || format.equals("xml")) {
            saveAsXML(users, new File(file));
        } else if (format.equals("JSON") || format.equals("json")) {
            saveAsJSON(users, new File(file));
        } else {
            System.out.println("unrecognized format " + format);
        }
    }

    private void saveAsJSON(List<UserData> users, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXML(List<UserData> users, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(UserData.class);
        String xml = xStream.toXML(users);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<UserData> users, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (UserData user : users) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"
                        , user.getFirstname(), user.getLastname(), user.getAddress()
                        , user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone()
                        , user.getEmail1(), user.getEmail2(), user.getEmail3(),
                        user.getGroups().iterator().next().getName()));
            }
        }
    }

    private List<UserData> generateUsers(int count) throws IOException {
        connectDB();
        Groups groups = getGroups();
        if (groups.size() == 0) {
            app.init();
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testnew"));
            app.stop();
        }
        List<UserData> users = new ArrayList<UserData>();
        for (int i = 0; i < count; i++) {
            users.add(new UserData().withFirstname(String.format("FirstName %s", i))
                    .withLastname(String.format("LastName %s", i))
                    .withAddress(String.format("Address %s", i))
                    .withHomePhoneNumber(phoneGenerator())
                    .withMobilePhoneNumber(phoneGenerator())
                    .withWorkPhoneNumber(phoneGenerator())
                    .withEmail1(String.format("testemail1_%s@test.com", i))
                    .withEmail2(String.format("testemail2_%s@test.com", i))
                    .withEmail3(String.format("testemail3_%s@test.com", i))
                    .inGroup(groups.iterator().next()));
        }
        return users;
    }

    private static String phoneGenerator() {
        String phone = "+7(900)";
        phone = phone + randomGenerate() + randomGenerate() + randomGenerate() +
                "-" + randomGenerate() + randomGenerate() + "-" + randomGenerate() + randomGenerate();
        return phone;
    }

    private static int randomGenerate() {
        int a = (int) (Math.random() * 10);
        return a;
    }

    private void connectDB() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private Groups getGroups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

}
