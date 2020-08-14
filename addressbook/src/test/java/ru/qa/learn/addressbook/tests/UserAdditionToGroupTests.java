package ru.qa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;
import ru.qa.learn.addressbook.model.UserData;
import ru.qa.learn.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAdditionToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecinditions() {
        Users users = app.db().users();                                         //Получаем список существующих пользователей
        if (app.db().groups().size() == 0) {                                    //Проверяем количество существующих групп
            app.goTo().groupPage();                                             //Если нет ни одной группы то переходим на страницу групп
            app.group().create(new GroupData().withName("testnew"));            //И добавляем новую группу с заданным именем
        }
        if (users.size() == 0) {                                                //Проверяем количество пользователей
            app.goTo().homePage();                                              //Если нет ни одного пользователя, то переходим на домашнюю страницу
            app.user().create(new UserData()                                    //И создаём нового пользователя с заданными параметрами
                            .withLastname("Михаил")                             //Имя
                            .withAddress("Михайлович")                          //Адресс
                            .withFirstname("Буслаев")                           //Фамилия
                            .withHomePhoneNumber("+79009009090")                //Домашний телефон
                            .withEmail1("test@test.com")                        //E-mail
                            .inGroup(app.db().groups().iterator().next())       //Группа
                    , true);
        }
            for (UserData user : users){                                        //То по всему списку пользователей
                if (user.getGroups().size() == app.db().groups().size()){       //Проверяем каждого пользователя, состоит ли он во всех группах
                    users.remove(user);                                         //И удаляем из списка тех, кто состоит во всех
                }
            }
            if (users.size() == 0){                                             //Если в итоге список становится пустым
                app.goTo().groupPage();                                         //То переходим на страницу групп
                app.group().create(new GroupData().withName("testnew3"));       //И добавляем новую группу с заданным именем
            }
    }

    @Test
    public void additionToGroupTest(){
        app.goTo().homePage();                                                                      //Переходим на домашнюю страницу для дальнейшей работы с данными на ней
        Groups groups = app.db().groups();                                                          //Считываем список существующих групп
        Users users = app.db().users();                                                             //Получаем список пользователей
        for (UserData user : users){                                                                //По всему списку пользователей
            if (user.getGroups().size() == app.db().groups().size()){                               //Проверяем, состоит ли пользователь во всех группах
                users.remove(user);                                                                 //И удаляем из списка тех, кто состоит во всех
            }
        }
        UserData user = users.iterator().next();                                                    //Из оставшегося списка пользователей берём случайного для дальнейшей работы с ним
        if (user.getGroups().size() != groups.size()){                                              //Сравниваем количество существующих групп с количеством групп пользователя
            GroupData additionGroup = app.user().groupsWithoutUser(user,groups).iterator().next();  //Если количество не совпадает, то ищем все группы, в которых пользователь не состоит и берём случайную

            Groups before = user.getGroups();                                                       //Сохраняем список групп, в которых состоит пользователь для дальнейшего сравнения

            app.user().addToGroup(user, additionGroup);                                             //Добавляем пользователя в выбранную группу

            before.add(additionGroup);                                                              //Добавляем выбранну группу к исходному списку групп пользователя
            Groups after = user.getGroups();                                                        //Считываем из базы ьекущий список групп пользователя
            assertThat(after,equalTo(before));                                                      //Сравниваем списки групп пользователя "до модификации + добавленная группа" и "после модицикации"
        } else {
            System.out.println("Пользователь уже добавлен во все группы");                          //Если количество групп совпадает, то выводим сообщение
        }
    }
}
