package tripcomposer.task.request.deserializer;

import com.google.gson.*;
import tripcomposer.task.model.CityVO;
import tripcomposer.task.model.CountryVO;
import tripcomposer.task.model.ResponseVO;
import tripcomposer.task.request.Poster;
import tripcomposer.task.service.CountryService;

import javax.ejb.EJB;
import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by vika on 05.11.15.
 */
public class ResponseDeserializer implements JsonDeserializer<ResponseVO> {

    @EJB
    CountryService countryService;

    @EJB
    Poster poster;

    public ResponseVO deserializeResponse(String responseString) {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CountryVO.class, new CountryDeserializer());
        gsonBuilder.registerTypeAdapter(CityVO.class, new CityDeserializer());
        gsonBuilder.registerTypeAdapter(ResponseVO.class, new ResponseDeserializer());
        final Gson gson = gsonBuilder.create();
        BufferedReader reader = new BufferedReader(new StringReader(responseString));
        ResponseVO responseVO = gson.fromJson(reader, ResponseVO.class);
        return responseVO;

    }

    public void saveResponse(String echo) {
        countryService.saveCountries(deserializeResponse(poster.sendPostRequest(echo)).getCountries());
    }

    @Override
    public ResponseVO deserialize(final JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final Long time = jsonObject.get("time").getAsLong();
        final String echo = jsonObject.get("echo").getAsString();
        CountryVO[] countries = jsonDeserializationContext.deserialize(jsonObject.get("countries"), CountryVO[].class);

        final ResponseVO responseVO = new ResponseVO();
        responseVO.setTime(time);
        responseVO.setEcho(echo);
        responseVO.setCountries(new HashSet<>(Arrays.asList(countries)));
        return responseVO;
    }

}
