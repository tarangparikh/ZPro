package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DBOperationDAO {
    private Session currentSession;
    private Transaction currentTransaction;
    public DBOperationDAO(){}
    public Session openCurrentSession(){
        currentSession = PersistenceDAO.getSessionFactory().getCurrentSession();
        return currentSession;
    }
    public void closeCurrentSession(){
        this.currentSession.close();
    }
    public Session openCurrentSessionWithTransaction(){
        currentSession = PersistenceDAO.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();;
        currentSession.close();
    }
    public Session getCurrentSession(){
        return this.currentSession;
    }
    public Transaction getCurrentTransaction(){
        return this.currentTransaction;
    }
    public Integer insert(Object object){
        return (Integer)getCurrentSession().save(object);
    }
    public <T> List<T> getList(Class<T> tClass,String query){
        Query<T> q = getCurrentSession().createQuery(query,tClass);
        return q.list();
    }
}
