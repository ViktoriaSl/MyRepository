package tripcomposer.task.unit.request;

import org.json.JSONObject;
import org.junit.Test;
import tripcomposer.task.request.Poster;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by vika on 03.11.15.
 */
public class RequestTest {


    private static final String ECHO_MASSAGE = "test echo";

    /**
     * ECHO_MASSAGE that sends should return the same value
     */
    @Test
    public void testResponseShouldReturnTheSameEcho() {
        Poster poster = new Poster();
        JSONObject jsonRequest = new JSONObject(poster.sendPostRequest(ECHO_MASSAGE));
        assertEquals(ECHO_MASSAGE, jsonRequest.get("echo"));
    }

    @Test
    public void testResponseTimeValueShouldBeLong() {
        Poster poster = new Poster();
        JSONObject jsonRequest = new JSONObject(poster.sendPostRequest(ECHO_MASSAGE));
        assertTrue(jsonRequest.get("time") instanceof Long);
    }


}
