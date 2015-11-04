package tripcomposer.task.model.conversion;

import tripcomposer.task.entity.impl.CountryEntity;
import tripcomposer.task.model.CountryVO;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vika on 30.10.15.
 */
public class CountryConverter {
    public static CountryEntity convertVOToEntity(CountryVO countryVO) {
        CountryEntity countryEntityForConversion = new CountryEntity();
        if (countryVO.getId() != null) {
            countryEntityForConversion.setId(countryVO.getId());
        }
        if (countryVO.getCountryName() != null) {
            countryEntityForConversion.setCountryName(countryVO.getCountryName());
        }
        if (countryVO.getCountryISOCode() != null) {
            countryEntityForConversion.setCountryISOCode(countryVO.getCountryISOCode());
        }
        if(countryVO.getCities()!=null) {
            countryEntityForConversion.setCities(CityConverter.convertVOsToEntities(countryVO.getCities()));
        }
        return countryEntityForConversion;
    }


    public static Set<CountryEntity> convertVOsToEntities(Collection<CountryVO> countryVOs) {
        Set<CountryEntity> countryEntities = new HashSet<>(countryVOs.size());
        countryEntities.addAll(countryVOs.stream()
                .map(CountryConverter::convertVOToEntity)
                .collect(Collectors.toList()));
        return countryEntities;
    }
  }
