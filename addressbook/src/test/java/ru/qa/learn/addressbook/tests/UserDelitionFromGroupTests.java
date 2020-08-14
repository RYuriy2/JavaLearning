package ru.qa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDelitionFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePrecinditions() {
        Users users = app.db().users();                                 //Получаем список всех существующих пользователей
        if (app.db().groups().size() == 0) {                            //Проверяем количество существующих групп
            app.goTo().groupPage();                                     //Если нет ни одной группы то переходим на страницу групп
            app.group().create(new GroupData().withName("testnew1"));   //И добавляем новую группу с заданным именем
        }
        if (app.db().users().size() == 0) {                             //Проверяем количество пользователей
            app.goTo().homePage();                                      //Если нет ни одного пользователя. то переходим на домашнюю страницу
            app.user().create(new UserData()                            //И создаём нового пользователя с заданными параметрами
                    .withLastname("Михаил")                             //Имя
                    .withAddress("Михайлович")                          //Адресс
                    .withFirstname("Буслаев")                           //Фамилия
                    .withHomePhoneNumber("+79009009090")                //Домашний телефон
                    .withEmail1("test@test.com")                        //E-mail
                    .inGroup(app.db().groups().iterator().next())       //Группа
                    , true);
        } else {                                                        //Если есть хотя бы один пользователь
            for (UserData user : users){                                //То по всему списку пользователей
                if (user.getGroups().size() == 0){                      //Проверяем, состоит ли он хотя бы в одной группе
                    users.remove(user);                                 //Удаляем из списка пользователей тех, кто не состоит ни в одной группе
                }
            }
            if (users.size() == 0){                                     //Если в итоге ни один пользователь не состоит ни в одной группе
                app.user().addToGroup(app.db().users().iterator().next()//Добавляем случайного пользователя
                        ,app.db().groups().iterator().next());          //В случайную группу
            }
        }
    }

    @Test
    public void userDelitionFromGroupTest(){
        app.goTo().homePage();                                                  //Переходим на домашнюю страницу для дальнейшей работы
        Users users = app.db().users();                                         //Выбираем пользователя, с которым будем работать
        for (UserData user : users){                                            //То по всему списку пользователей
            if (user.getGroups().size() == 0){                                  //Проверяем, состоит ли он хотя бы в одной группе
                users.remove(user);                                             //Удаляем из списка пользователей тех, кто не состоит ни в одной группе
            }
        }
        UserData user = users.iterator().next();                                //Из оставшегося списка берём случайного пользователя

        if (user.getGroups().size() != 0){                                      //Проверяем количество групп, в которых присутствует пользователь
            Groups before = user.getGroups();                                   //Если количество больше 0, то сохраняем список групп пользователя до модицикации
            GroupData group = user.getGroups().iterator().next();               //Выбираем случайную группу из групп пользователя
            app.user().delitionUserFromGroup(group, user);                      //Удаляем пользователя из выбранной группы
            before.remove(group);                                               //Удаляем выбранную группу из исходного списка
            Groups after = user.getGroups();                                    //Получаем сисок групп пользователя из БД после редактирвоания
            assertThat(after,equalTo(before));                                  //Сравниваем списки групп пользователя "До модификации - удал1нная групп" и "После модификации"
        } else {
            System.out.println("Пользователь не состоит ни в одной группе");    //Есди пользователь не состоит ни в одной группе, то выводим сообщение.
        }
    }
}
