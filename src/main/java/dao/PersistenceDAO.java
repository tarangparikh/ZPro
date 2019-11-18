package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PersistenceDAO {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        try{
            Configuration configuration = new Configuration();
            String HEROKU_JDBC_URL = System.getenv("JDBC_DATABASE_URL");
            if(HEROKU_JDBC_URL!=null) configuration.setProperty("hibernate.connection.url",HEROKU_JDBC_URL);
            return configuration.configure().buildSessionFactory();
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
