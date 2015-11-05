package tripcomposer.task.entity.impl;


import tripcomposer.task.entity.GenericEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by vika on 18.03.15.
 */
@Entity
@Table(name = "city", schema = "public")
public class CityEntity implements Serializable, GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "City name should be defined")
    @Basic
    @Column(name = "city_name", nullable = true, insertable = true, updatable = true)
    private String cityName;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity countries;

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

    public CountryEntity getCountries() {
        return countries;
    }

    public void setCountry(CountryEntity countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityEntity)) return false;

        CityEntity that = (CityEntity) o;

        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }

    public CityEntity() {
    }

    public CityEntity(String cityName) {
        this.cityName = cityName;
    }

    public CityEntity(String cityName, CountryEntity countries) {
        this.cityName = cityName;
        this.countries = countries;
    }


}
