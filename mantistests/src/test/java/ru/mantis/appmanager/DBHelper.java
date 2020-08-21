package ru.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.mantis.model.UserData;
import ru.mantis.model.Users;
import java.util.List;

public class DBHelper {

    private SessionFactory sessionFactory;
    private final ApplicationManager app;

    public DBHelper(ApplicationManager app){
        this.app = app;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Users users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }
}