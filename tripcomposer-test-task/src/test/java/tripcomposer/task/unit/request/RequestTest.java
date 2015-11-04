package tripcomposer.task.unit.request;

import org.json.simple.JSONObject;
import org.junit.Test;
import tripcomposer.task.request.Poster;

import static org.junit.Assert.assertEquals;

/**
 * Created by vika on 03.11.15.
 */
public class RequestTest {


    private static final String ECHO_MASSAGE = "test echo";

    /**
     * ECHO_MASSAGE that sends should return the same value
     */
    @Test
    public void testResponseShouldReturnTheSameEcho(){
        Poster poster = new Poster();
        JSONObject jsonRequest =(JSONObject) poster.sendPostRequest(ECHO_MASSAGE);
        assert jsonRequest != null;
        assertEquals(ECHO_MASSAGE, jsonRequest.get("echo"));
    }


}
