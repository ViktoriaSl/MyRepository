package tripcomposer.task.request.deserializer;

import com.google.gson.*;
import tripcomposer.task.model.CityVO;
import tripcomposer.task.model.CountryVO;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by vika on 05.11.15.
 */
public class CountryDeserializer implements JsonDeserializer<CountryVO> {

    @Override
    public CountryVO deserialize(final JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final String countryName = jsonObject.get("countryName").getAsString();
        final String countryISOCode = jsonObject.get("countryISOCode").getAsString();

        CityVO[] cities = jsonDeserializationContext.deserialize(jsonObject.get("cities"), CityVO[].class);

        final CountryVO countryVO = new CountryVO();
        countryVO.setCountryName(countryName);
        countryVO.setCountryISOCode(countryISOCode);
        countryVO.setCities(new HashSet<>(Arrays.asList(cities)));
        return countryVO;
    }
}
