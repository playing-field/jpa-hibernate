package lk.ijse.dep.web.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;

public interface SuperDAO {

//    public void setSession(Session session) throws Exception;
    public void setEntityManager(EntityManager entityManager) throws Exception;

}
