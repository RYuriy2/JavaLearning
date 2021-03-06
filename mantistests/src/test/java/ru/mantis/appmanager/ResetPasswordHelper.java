package ru.mantis.appmanager;

import org.openqa.selenium.By;
import ru.mantis.model.MailMessage;
import ru.mantis.model.UserData;
import ru.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void login(String username, String password){
        wd.get(app.getProperty("web.baseURL") + "/index.php");
        type(By.name("username"),username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"),password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void resetPassword(String username){
        wd.get("http://localhost/mantis/manage_user_page.php");
        click(By.linkText((String)username));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void confirmResetPassword(UserData user, String password) throws IOException, MessagingException {
        String email = user.getEmail();
        String username = user.getUsername();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        wd.get(confirmationLink);
        type(By.cssSelector("input[id='realname']"), username);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("button[type='submit']"));
    }
}
