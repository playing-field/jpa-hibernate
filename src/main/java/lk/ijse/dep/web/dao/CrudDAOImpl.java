package lk.ijse.dep.web.dao;

import lk.ijse.dep.web.entity.SuperEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudDAOImpl<T extends SuperEntity, K extends Serializable> implements CrudDAO<T, K> {
    //    private Session session;

    private EntityManager entityManger;
    private Class<T> entityClass;

    public CrudDAOImpl() {
        entityClass = (Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

//    protected Session getSession() {
//        return this.session;

    protected EntityManager getEntityManager() {
        return this.entityManger;
    }

    //    }
    @Override
    public void setEntityManager(EntityManager entityManager) throws Exception {
        this.entityManger = entityManager;
    }
//    @Override

//    public void setSession(Session session) throws Exception {
//        this.session = session;
//    }

    @Override
    public void save(T entity) throws Exception {
        entityManger.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        entityManger.merge(entity);
    }

    @Override
    public void delete(K key) throws Exception {
        entityManger.remove(entityManger.getReference(entityClass, key));
    }

    @Override
    public List<T> getAll() throws Exception {
        return entityManger.createQuery("FROM " + entityClass.getName()).getResultList();
    }

    @Override
    public T get(K key) throws Exception {
        return entityManger.find(entityClass, key);
    }
}
