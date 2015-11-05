package tripcomposer.task.service;

import com.sun.istack.internal.NotNull;
import tripcomposer.task.model.CountryVO;

import javax.validation.Valid;
import java.util.Set;

/**
 * Created by vika on 25.10.15.
 */
public interface CountryService {

    Set<CountryVO> saveCountries(@NotNull @Valid Set<CountryVO> countryVOs);
}
