package tripcomposer.task.model;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * Created by vika on 25.10.15.
 */
public class CityVO implements Serializable {

    @Null
    private Long id;
    @NotNull
    private String cityName;

    private CountryVO country;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CountryVO getCountry() {
        return country;
    }

    public void setCountry(CountryVO country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CityVO{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", country=" + country +
                '}';
    }

    public CityVO() {
    }

    public CityVO(String cityName) {
        this.cityName = cityName;
    }


}
