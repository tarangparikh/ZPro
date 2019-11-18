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
            Map<String,String> jdbcUrlSettings = new HashMap<>();
            String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
            if (null != jdbcDbUrl) {
                jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    configure("hibernate.cfg.xml").
                    applySettings(jdbcUrlSettings).
                    build();
            return new Configuration().configure().buildSessionFactory(serviceRegistry);
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
