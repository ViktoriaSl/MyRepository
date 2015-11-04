package tripcomposer.task.model;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by vika on 25.10.15.
 */

public class CountryVO  implements Serializable{

    @Null
    private Long id;
    @NotNull
    private String countryName;
    @NotNull
    private String countryISOCode;

    private Set<CityVO> cityVOSet;


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
        return cityVOSet;
    }

    public void setCityVOSet(Set<CityVO> cityVOSet) {
        this.cityVOSet = cityVOSet;
    }

    public CountryVO() {
    }

    public CountryVO(String countryName, String countryISOCode) {
        this.countryName = countryName;
        this.countryISOCode = countryISOCode;
    }

    public CountryVO(String countryName, String countryISOCode, Set<CityVO> cityVOSet) {
        this.countryName = countryName;
        this.countryISOCode = countryISOCode;
        this.cityVOSet = cityVOSet;
    }
}
