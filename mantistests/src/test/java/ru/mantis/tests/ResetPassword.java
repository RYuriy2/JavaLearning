package ru.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.mantis.model.MailMessage;
import ru.mantis.model.UserData;
import ru.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPassword extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void resetPasswordTest() throws IOException, MessagingException {
        Users users = app.db().users();
        String username = "userResetPassword";
        String email = "";
        String newPassword = "QQqq2222!";
        app.rsPass().login(app.getProperty("web.login"),app.getProperty("web.password"));
        app.rsPass().resetPassword(username);
        app.rsPass().confirmResetPassword(users,username,newPassword,email);
        assertTrue(app.newSession().login(username,newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
