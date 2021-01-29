package lk.ijse.dep.web.business;

import org.hibernate.Session;

import javax.persistence.EntityManager;

public interface SuperBO {

//    public void setSession(Session session) throws Exception;
    public void setEntityManager(EntityManager entityManager) throws Exception;

}
