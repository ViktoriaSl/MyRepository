package tripcomposer.task.model;

import java.util.Set;

/**
 * Created by vika on 05.11.15.
 */
public class ResponseVO {

    private Long time;
    private Set<CountryVO> countries;
    private String echo;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Set<CountryVO> getCountries() {
        return countries;
    }

    public void setCountries(Set<CountryVO> countries) {
        this.countries = countries;
    }

    public String getEcho() {
        return echo;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }

    @Override
    public String toString() {
        return "Response{" +
                "time=" + time +
                ", countries=" + countries +
                ", echo='" + echo + '\'' +
                '}';
    }
}
