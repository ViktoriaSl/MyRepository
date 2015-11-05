package tripcomposer.task.request;

/**
 * Created by vika on 05.11.15.
 */
public class MyRequest {

    private final String key = "$1$12309856$euBrWcjT767K2sP9MHcVS/";
    private String echo = "test request";

    public String getKey() {
        return key;
    }

    public String getEcho() {
        return echo;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }

    public MyRequest(String echo) {
        this.echo = echo;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "key='" + key + '\'' +
                ", echo='" + echo + '\'' +
                '}';
    }
}
