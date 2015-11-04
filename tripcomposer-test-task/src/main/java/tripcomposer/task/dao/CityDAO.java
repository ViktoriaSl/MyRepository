package tripcomposer.task.dao;

import tripcomposer.task.entity.impl.CityEntity;

import javax.ejb.Stateless;

/**
 * Created by vika on 24.10.15.
 */
@Stateless(name = "CityBean")
public class CityDAO extends GenericDAO<CityEntity> {

    public CityDAO() {
        setEntityClass(CityEntity.class);
    }
 }
