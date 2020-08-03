package ru.qa.learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPhoneTests extends TestBase {

    @Test
    public void testContactPhone(){
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getHomePhone(), equalTo(cleaned(userInfoFromEditForm.getHomePhone())));
        assertThat(user.getMobilePhone(), equalTo(cleaned(userInfoFromEditForm.getMobilePhone())));
        assertThat(user.getWorkPhone(), equalTo(cleaned(userInfoFromEditForm.getWorkPhone())));
    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
