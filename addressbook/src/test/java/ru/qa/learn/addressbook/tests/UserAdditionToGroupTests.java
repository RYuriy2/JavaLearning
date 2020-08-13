package ru.qa.learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.learn.addressbook.model.GroupData;
import ru.qa.learn.addressbook.model.Groups;
import ru.qa.learn.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAdditionToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecinditions() {
        if (app.db().groups().size() == 0) {                            //Проверяем количество существующих групп
            app.goTo().groupPage();                                     //Если нет ни одной группы то переходим на страницу групп
            app.group().create(new GroupData().withName("testnew"));    //И добавляем новую группу с заданным именем
        }
    }

    @Test
    public void additionToGroupTest(){
        app.goTo().homePage();                                                                      //Переходим на домашнюю страницу для дальнейшей работы с данными на ней
        Groups groups = app.db().groups();                                                          //Считываем список существующих групп
        UserData user = app.db().users().iterator().next();                                         //Выбираем случайного пользователя для добавления в групу

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
