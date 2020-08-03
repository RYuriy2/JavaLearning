package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserEmailTests extends TestBase {

    @Test
    public void testUserEmail(){
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getAllEmail(), equalTo(mergeEmail(userInfoFromEditForm)));
    }

    private String mergeEmail(UserData user) {
        return Arrays.asList(user.getEmail1(),user.getEmail2(),user.getEmail3()).stream().filter((s)->!s.equals(""))
                .map(this::cleaned).collect(Collectors.joining("\n"));
    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
