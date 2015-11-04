package tripcomposer.task.request.conversion;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import tripcomposer.task.model.CityVO;
import tripcomposer.task.model.CountryVO;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by vika on 30.10.15.
 */
public class Parser {

    public static Set<CountryVO> convertCountryJSONsToVOs(Object responseObject) {
        Set<CountryVO> nestedCountryVOs = new HashSet<>();
        CountryVO currentCountryVO;
        for (Object countryObject : getNestedObject(responseObject, "countries")) {
            JSONObject jsonObjectWithCountries = (JSONObject) countryObject;
            currentCountryVO = new CountryVO((String.valueOf(jsonObjectWithCountries
                    .get("countryName"))), String.valueOf(jsonObjectWithCountries
                    .get("countryISOCode")));
            currentCountryVO.setCityVOSet(convertCityJSONsToVOs(countryObject));
            nestedCountryVOs.add(currentCountryVO);
        }
        return nestedCountryVOs;
    }


    private static Set<CityVO> convertCityJSONsToVOs(Object countryObject) {
        Set<CityVO> nestedCityVOs = new HashSet<>();
        for (Object cityObject : getNestedObject(countryObject, "cities")) {
            JSONObject jsonObjectWithCities = (JSONObject) cityObject;
            nestedCityVOs.add(new CityVO(String.valueOf(jsonObjectWithCities.get("cityName"))));

        }
        return nestedCityVOs;
    }


    private static Set<Object> getNestedObject(Object objectWithNestedParts, String objectWithNestedPartsName) {
        JSONObject jsonObjectWithNestedParts = (JSONObject) objectWithNestedParts;
        assert jsonObjectWithNestedParts != null;
        JSONArray msg = (JSONArray) jsonObjectWithNestedParts.get(objectWithNestedPartsName);
        Iterator iterator = msg.iterator();
        Set<Object> listOfTheNestedObjects = new HashSet<>();
        while (iterator.hasNext()) {
            listOfTheNestedObjects.add(iterator.next());
        }
        return listOfTheNestedObjects;
    }

}
