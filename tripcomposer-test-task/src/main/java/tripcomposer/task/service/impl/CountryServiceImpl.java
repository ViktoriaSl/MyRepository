package tripcomposer.task.service.impl;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tripcomposer.task.dao.CityDAO;
import tripcomposer.task.dao.CountryDAO;
import tripcomposer.task.entity.impl.CityEntity;
import tripcomposer.task.entity.impl.CountryEntity;
import tripcomposer.task.model.CountryVO;
import tripcomposer.task.model.conversion.CountryConverter;
import tripcomposer.task.service.CountryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import java.util.Set;

import static tripcomposer.task.model.conversion.CountryConverter.convertVOsToEntities;


/**
 * Created by vika on 25.10.15.
 */
@Stateless
public class CountryServiceImpl implements CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryConverter.class);
    @EJB
    CountryDAO countryDAO;

    @EJB
    CityDAO cityDAO;

    @Override
    public Set<CountryVO> saveCountries(@NotNull @Valid Set<CountryVO> countryVOs) {
        Set<CountryEntity> countryEntitiesResponse = convertVOsToEntities(countryVOs);
        for (CountryEntity currentCountryForSaving : countryEntitiesResponse) {
            currentCountryForSaving = countryDAO.save(currentCountryForSaving);
            try {
                saveCitiesToCountry(currentCountryForSaving.getCities(), currentCountryForSaving);
            } catch (NullPointerException e) {
                log.warn("Country " + currentCountryForSaving.getCountryName() + " don't have cities");
            }
        }
        return countryVOs;
    }


    private void saveCitiesToCountry(Set<CityEntity> cityForSaving, CountryEntity currentCountry) {
        for (CityEntity cityEntity : cityForSaving) {
            cityEntity.setCountry(currentCountry);
            cityDAO.save(cityEntity);
        }
    }
}



