package tripcomposer.task.model.conversion;

import tripcomposer.task.model.CityVO;
import tripcomposer.task.entity.impl.CityEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vika on 28.10.15.
 */
public class CityConverter {

    public static CityEntity convertVOToEntity(CityVO cityVO) {
        CityEntity cityEntity = new CityEntity();
        if (cityVO.getId() != null) {
            cityEntity.setId(cityVO.getId());
        }
        if (cityVO.getCityName() != null) {
            cityEntity.setCityName(cityVO.getCityName());
        }
           return cityEntity;
    }

    public static Set<CityEntity> convertVOsToEntities(Collection<CityVO> cityVOs) {
        Set<CityEntity> cityEntities = new HashSet<>(cityVOs.size());
        cityEntities.addAll(cityVOs.stream()
                .map(CityConverter::convertVOToEntity)
                .collect(Collectors.toList()));
        return cityEntities;
    }



  }
