package ru.qa.learn.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.qa.learn.addressbook.model.UserData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

    @Parameter(names = "-c",description = "GroupsCount")
    public int count;

    @Parameter(names = "-f",description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        save(users, new File(file));
    }

    private void save(List<UserData> users, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (UserData user : users) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n"
                    , user.getFirstname(), user.getLastname(), user.getAddress()
                    , user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone()
                    , user.getEmail1(), user.getEmail2(), user.getEmail3()));
        }
        writer.close();
    }

    private List<UserData> generateUsers(int count) {
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
                    .withEmail3(String.format("testemail3_%s@test.com", i)));
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

}
