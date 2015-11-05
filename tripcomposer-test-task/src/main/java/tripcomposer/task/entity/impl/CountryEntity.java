package tripcomposer.task.entity.impl;


import tripcomposer.task.entity.GenericEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by vika on 24.10.15.
 */
@Entity
@Table(name = "country", schema = "public")
public class CountryEntity implements Serializable, GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Country name should be defined")
    @Basic
    @Column(name = "country", nullable = true, insertable = true, updatable = true)
    private String countryName;

    @NotNull(message = "Country ISO code should be defined")
    @Basic
    @Column(name = "country_iso_code", nullable = true, insertable = true, updatable = true)
    private String countryISOCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countries", cascade = {CascadeType.REMOVE})
    private Set<CityEntity> city;


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

    public Set<CityEntity> getCities() {
        return city;
    }

    public void setCities(Set<CityEntity> city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryEntity)) return false;

        CountryEntity that = (CountryEntity) o;

        if (countryISOCode != null ? !countryISOCode.equals(that.countryISOCode) : that.countryISOCode != null)
            return false;
        if (countryName != null ? !countryName.equals(that.countryName) : that.countryName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        result = 31 * result + (countryISOCode != null ? countryISOCode.hashCode() : 0);
        return result;
    }

    public CountryEntity(Long id, String countryName, String countryISOCode) {
        this.id = id;
        this.countryName = countryName;
        this.countryISOCode = countryISOCode;
    }


    public CountryEntity(String countryName, String countryISOCode) {
        this.countryName = countryName;
        this.countryISOCode = countryISOCode;
    }


    public CountryEntity() {
    }
}
