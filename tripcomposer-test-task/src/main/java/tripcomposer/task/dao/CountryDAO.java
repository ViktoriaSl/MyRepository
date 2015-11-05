package tripcomposer.task.dao;

import tripcomposer.task.entity.impl.CountryEntity;

import javax.ejb.Stateless;

/**
 * Created by vika on 24.10.15.
 */
@Stateless(name = "CountryBean")
public class CountryDAO extends GenericDAO<CountryEntity> {
    public CountryDAO() {
        setEntityClass(CountryEntity.class);
    }
}
