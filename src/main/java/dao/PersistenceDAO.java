package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class PersistenceDAO {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        try{
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
            standardServiceRegistryBuilder.applySettings(configuration.getProperties());
            String HEROKU_JDBC_URL = System.getenv("JDBC_DATABASE_URL");
            if(HEROKU_JDBC_URL!=null) {
                Map<String,String> map = new HashMap<>();
                map.put("hibernate.connection.url",HEROKU_JDBC_URL);
                standardServiceRegistryBuilder.applySettings(map);
            }
            ServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();
            return configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static void shutdown(){
        sessionFactory.close();
    }
}
