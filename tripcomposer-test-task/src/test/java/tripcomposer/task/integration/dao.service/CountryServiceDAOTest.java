package tripcomposer.task.integration;

import javafx.util.Pair;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.*;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tripcomposer.task.dto.CountryService;
import tripcomposer.task.model.CityVO;
import tripcomposer.task.model.CountryVO;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by vika on 31.10.15.
 */

@RunWith(Arquillian.class)
@Cleanup(phase = TestExecutionPhase.BEFORE, strategy = CleanupStrategy.STRICT)
@UsingDataSet(value = "datasets/dao-service-test/country/one-country.json")
public class CountryServiceDAOTest {

    private static final Set<CountryVO> countriesWithCitiesForSaving;
    private static final Set<CountryVO> countriesWithoutCitiesForSaving;
    private static final String DS_DIR = "datasets/dao-service-test/";
    private static final String DS_EMPTY = DS_DIR + "empty.json";
    private static final String DS_COUNTRY = DS_DIR + "country/one-country.json";
    private static final String DS_COUNTRY_AFTER_SAVE = DS_DIR + "country/expected-countries-without-cities-after-save.json";
    private static final String DS_COUNTRY_WITH_CITIES_AFTER_SAVE = DS_DIR + "country/expected-countries-with-cities-after-save.json";

    static {
       countriesWithCitiesForSaving = new HashSet<>(Arrays
                .asList(new CountryVO("Ukraine", "UA", new HashSet<>(Arrays.asList(
                                new CityVO("Odessa"),
                                new CityVO("Kyiv")))),
                        new CountryVO("Poland", "PL", new HashSet<>(Arrays.asList(
                                new CityVO("Warszawa"),
                                new CityVO("Krakow"))))));
       countriesWithoutCitiesForSaving = new HashSet<>(Arrays
               .asList(new CountryVO("Ukraine", "UA"),
                       new CountryVO("Poland", "PL")));
  }
    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addPackage("tripcomposer.task.dao")
                .addPackage("tripcomposer.task.dao.exception")
                .addPackage("tripcomposer.task.model")
                .addPackage("tripcomposer.task.model.conversion")
                .addPackage("tripcomposer.task.conversion")
                .addPackage("tripcomposer.task.entity")
                .addPackage("tripcomposer.task.entity.impl")
                .addPackage("tripcomposer.task.dto")
                .addPackage("tripcomposer.task.dto.impl")
                .addPackage("org.assertj.core.api")
                .addPackage("org.assertj.core.internal")
                .addPackage("org.assertj.core.error")
                .addPackage("org.assertj.core.util.introspection")
                .addPackage("org.assertj.core.presentation")
                .addPackage("org.assertj.core.util")
                .addClass(Pair.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
       return war;
    }

    @EJB
    CountryService countryService;

    @Test
    public void testCountryServiceShouldBeInjected() {
        assertNotNull(countryService);
    }


    @Test
    @ShouldMatchDataSet(value = {DS_EMPTY, DS_COUNTRY_AFTER_SAVE},excludeColumns = {"id"})
    public void testSaveCountriesWithoutCitiesShouldReturnCountryVOs() {
        assertEquals(countriesWithoutCitiesForSaving,countryService.saveCountries(countriesWithoutCitiesForSaving));
    }

    @Test
    @ShouldMatchDataSet(value = {DS_EMPTY, DS_COUNTRY_WITH_CITIES_AFTER_SAVE},excludeColumns = {"id"})
    public void testSaveCountriesWithCitiesShouldReturnCountryVOs1() {
      assertEquals(countriesWithCitiesForSaving, countryService.saveCountries(countriesWithCitiesForSaving));
    }

    @Test
    @ShouldMatchDataSet(value = {DS_EMPTY, DS_COUNTRY},excludeColumns = {"id"})
    public void testSaveShouldThrowEJBException() {
        assertThatThrownBy(() -> countryService.saveCountries(null))
                .isInstanceOf(EJBException.class);
    }

    @Test
    @ShouldMatchDataSet(value = {DS_EMPTY, DS_COUNTRY},excludeColumns = {"id"})
    public void testSaveCityWithoutNameShouldThrowEJBException() {
        assertThatThrownBy(() -> countryService.saveCountries(new HashSet<>(Arrays
                .asList(new CountryVO[]{new CountryVO("Ukraine", "UA", new HashSet<>(Arrays.asList(
                        new CityVO(),
                        new CityVO())))}))))
                .isInstanceOf(EJBException.class);
    }

    @Test
    @ShouldMatchDataSet(value = {DS_EMPTY, DS_COUNTRY},excludeColumns = {"id"})
    public void testSaveCountryWithoutNameAndISOCodeShouldThrowEJBException() {
        assertThatThrownBy(() -> countryService.saveCountries(new HashSet<>(Arrays
                .asList(new CountryVO(), new CountryVO()))));
    }



}
