package tripcomposer.task.model;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by vika on 25.10.15.
 */

public class CountryVO implements Serializable {

    @Null
    private Long id;
    @NotNull
    private String countryName;
    @NotNull
    private String countryISOCode;

    private Set<CityVO> cities;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryISOCode() {
        return countryISOCode;
    }

    public void setCountryISOCode(String countryISOCode) {
        this.countryISOCode = countryISOCode;
    }

    public Set<CityVO> getCities() {
        return cities;
    }

    public void setCities(Set<CityVO> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "CountryVO{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryISOCode='" + countryISOCode + '\'' +
                ", cities=" + cities +
                '}';
    }

    public CountryVO() {
    }

    public CountryVO(String countryName, String countryISOCode) {
        this.countryName = countryName;
        this.countryISOCode = countryISOCode;
    }

    public CountryVO(String countryName, String countryISOCode, Set<CityVO> cities) {
        this.countryName = countryName;
        this.countryISOCode = countryISOCode;
        this.cities = cities;
    }


}
