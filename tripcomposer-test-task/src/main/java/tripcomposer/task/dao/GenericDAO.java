package tripcomposer.task.dao;

import tripcomposer.task.dao.exception.EntityIsAlreadyExistException;
import tripcomposer.task.entity.GenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Created by vika on 25.10.15.
 */
public class GenericDAO <ENTITY extends GenericEntity> {
    Class<ENTITY> entityClass;

   @PersistenceContext
    private EntityManager em;

    public GenericDAO() {
    }

    public void setEntityClass(Class<ENTITY> entityClass) {
        this.entityClass = entityClass;
    }

    public ENTITY save(@NotNull @Valid ENTITY entity) {
        if (entity.getId() != null && getEntityManager().find(entityClass, entity.getId()) != null) {
            throw new EntityIsAlreadyExistException("Field with id = "
                    + entity.getId()
                    + " already exists in database");
        } else {
            getEntityManager().persist(entity);
        }
        return entity;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }


}
