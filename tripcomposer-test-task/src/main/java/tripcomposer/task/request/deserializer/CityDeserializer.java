package tripcomposer.task.request.deserializer;

import com.google.gson.*;
import tripcomposer.task.model.CityVO;

import java.lang.reflect.Type;

/**
 * Created by vika on 05.11.15.
 */
public class CityDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final CityVO city = new CityVO();
        city.setCityName(jsonObject.get("cityName").getAsString());
        return city;
    }
}
