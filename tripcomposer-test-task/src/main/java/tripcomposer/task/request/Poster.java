package tripcomposer.task.request;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import tripcomposer.task.dto.CountryService;
import tripcomposer.task.request.exception.RequestNotCreatedException;
import tripcomposer.task.request.exception.ResponseNotCreatedException;

import javax.ejb.EJB;
import java.io.*;

import static tripcomposer.task.request.conversion.Parser.convertCountryJSONsToVOs;

/**
 * Created by vika on 28.10.15.
 */
public class Poster {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Poster.class);
    private static final String KEY = "$1$12309856$euBrWcjT767K2sP9MHcVS/";
    private static final String URL_FOR_REQUEST = "http://tripcomposer.net/rest/test/countries/get";
    private static final String CONTENT_TYPE = "application/json";

    @EJB
    CountryService countryService;


    public void saveRequest(String echo){
        countryService.saveCountries(
                convertCountryJSONsToVOs
                        (sendPostRequest(echo)));

    }

    public Object sendPostRequest(String echo)  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URL_FOR_REQUEST);
        StringEntity input;
        CloseableHttpResponse response;
        String responseString;
        Object responseObject = null;
        if(echo ==null){
            log.warn("Echo message is null");
        }
       try {
            input = createRequest(echo);
         } catch (UnsupportedEncodingException e) {
           throw new RequestNotCreatedException("JSON massage wasn't created");
        }
        assert input != null;
        input.setContentType(CONTENT_TYPE);
        httpPost.setEntity(input);
        try {
            response = httpClient.execute(httpPost);
            responseString = getStringFromInputStream(response.getEntity().getContent());
            JSONParser parser = new JSONParser();
            responseObject =  parser.parse(responseString);
        } catch (IOException|ParseException e) {
           throw new ResponseNotCreatedException("Response wasn't created successfully");
        }
        return responseObject;
     }


    private StringEntity createRequest(String echo) throws UnsupportedEncodingException {
          return new StringEntity("" +
                 "{" +
                 "\"key\": \""+KEY+"\"," +
                 "\"echo\": \""+echo+"\""+
                 "}");

    }

    private String getStringFromInputStream(InputStream is) {
        BufferedReader responseStreamForConversion = null;
        StringBuilder stringFromInputStream = new StringBuilder();
        String line;
        try {
          responseStreamForConversion = new BufferedReader(new InputStreamReader(is));
            while ((line = responseStreamForConversion.readLine()) != null) {
                stringFromInputStream.append(line);
            }

        } catch (IOException e) {
            log.error("String from InputStream wasn't converted successfully");
        } finally {
            if (responseStreamForConversion != null) {
                try {
                    responseStreamForConversion.close();
                } catch (IOException e) {
             log.error("String from InputStream wasn't converted successfully");
                }
            }
        }
        return stringFromInputStream.toString();

    }
}
