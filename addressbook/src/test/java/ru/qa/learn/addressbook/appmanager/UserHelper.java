package ru.qa.learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void initCreationNewUser() {
        click(By.linkText("add new"));
    }

    public void create(UserData user, boolean creation) {
        initCreationNewUser();
        fillUserData(user, creation);
        submitCreateNewUser();
        userCache = null;
        returnToHomePage();
    }

    public void submitCreateNewUser() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }


    public void fillUserData(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("address"), userData.getAddress());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("home"),userData.getHomePhone());
        type(By.name("mobile"), userData.getMobilePhone());
        type(By.name("work"),userData.getWorkPhone());
        type(By.name("email"), userData.getEmail1());
        type(By.name("email2"), userData.getEmail2());
        type(By.name("email3"), userData.getEmail3());
        attach(By.name("photo"),userData.getPhoto());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else Assert.assertFalse(isElementPresent(By.name("new_group")));
    }


    private void selectUserByID(int id) {
        wd.findElement(By.cssSelector(String.format("input[id='%s']",id))).click();
    }


    public void initEditUserByID(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }

    public void edit(UserData user) {
        initEditUserByID(user.getID());
        fillUserData(user, false);
        submitEditUser();
        userCache = null;
        returnToHomePage();
    }

    public void submitEditUser() {
        click(By.xpath("//input[@name='update']"));
    }


    public void delete(UserData user) {
        selectUserByID(user.getID());
        deleteSelectedUser();
        closeAlertPopUp();
        userCache = null;
        returnToHomePage();
    }

    public void deleteSelectedUser() {
        click(By.xpath("//input[@value='Delete']"));
    }


    public void returnToHomePage() {
        click(By.linkText("home"));
    }


    public boolean isThereUser() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }


    Users userCache = null;

    public Users all() {
        if (userCache != null) {
            return new Users(userCache);
        } else {
            userCache = new Users();
            List<WebElement> line = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr"));
            for (WebElement element : line) {
                List<WebElement> elements = element.findElements(By.tagName("td"));
                if (elements.size() != 0) {
                    String firstname = elements.get(2).getText();
                    String lastname = elements.get(1).getText();
                    String email = elements.get(4).getText();
                    String allPhones = elements.get(5).getText();
                    String address = elements.get(3).getText();
                    int id = Integer.parseInt(elements.get(0).findElement(By.name("selected[]")).getAttribute("id"));
                    userCache.add(new UserData().withID(id).withFirstname(firstname).withAddress(address).withLastname(lastname)
                            .withAllPhoneNumber(allPhones).withAllEmail(email));
                }
            }
            return new Users(userCache);
        }
    }


    public int getUserCount() {
        return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
    }


    public void closeAlertPopUp() {
        wd.switchTo().alert().accept();
    }

    public UserData infoFromEditForm(UserData user) {
        initEditUserByID(user.getID());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new UserData().withFirstname(firstname).withLastname(lastname)
                .withHomePhoneNumber(homephone).withMobilePhoneNumber(mobilephone).withWorkPhoneNumber(workphone)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3);
    }
}
