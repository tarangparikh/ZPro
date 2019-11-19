package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class PersistenceDAO {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final String POSTGRES_DRIVER_CLASS = "org.postgresql.Driver";
    private static final String POSTGRES_DIALECT = "org.hibernate.dialect.PostgreSQL10Dialect";
    private static final String LOCAL_MYSQL_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String LOCAL_MYSQL_DIALECT = "org.hibernate.dialect.MySQL57Dialect";
    private static final String LOCAL_MYSQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/zpro?userSSL=false";
    private static final String LOCAL_MYSQL_USER = "tarang";
    private static final String LOCAL_MYSQL_PASSWORD = "tarang";
    private static SessionFactory buildSessionFactory(){
        try{
            String HEROKU_POSTGRES = System.getenv("JDBC_DATABASE_URL");
            if (null != HEROKU_POSTGRES) {
                Map<String,String> HEROKU_SETTINGS = new HashMap<>();
                HEROKU_SETTINGS.put(Environment.URL, System.getenv("JDBC_DATABASE_URL"));
                HEROKU_SETTINGS.put(Environment.DRIVER, POSTGRES_DRIVER_CLASS);
                HEROKU_SETTINGS.put(Environment.DIALECT,POSTGRES_DIALECT);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                        configure("hibernate.cfg.xml").
                        applySettings(HEROKU_SETTINGS).
                        build();
                return new Configuration().configure().buildSessionFactory(serviceRegistry);
            }
            else{
                Map<String,String> LOCAL_SETTINGS = new HashMap<>();
                LOCAL_SETTINGS.put(Environment.URL,LOCAL_MYSQL_CONNECTION_URL);
                LOCAL_SETTINGS.put(Environment.DRIVER, LOCAL_MYSQL_DRIVER_CLASS);
                LOCAL_SETTINGS.put(Environment.DIALECT,LOCAL_MYSQL_DIALECT);
                LOCAL_SETTINGS.put(Environment.USER,LOCAL_MYSQL_USER);
                LOCAL_SETTINGS.put(Environment.PASS,LOCAL_MYSQL_PASSWORD);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                        configure("hibernate.cfg.xml").
                        applySettings(LOCAL_SETTINGS).
                        build();
                return new Configuration().configure().buildSessionFactory(serviceRegistry);
            }
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
