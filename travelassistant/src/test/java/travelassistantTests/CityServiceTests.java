package travelassistantTests;

import Models.City;
import Services.CityService;
import Repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CityServiceTests {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    public void testGetAllCities() {
        City city = new City();
        city.setId(1L);
        city.setName("New York");
        city.setCountry("USA");

        given(cityRepository.findAll()).willReturn(Collections.singletonList(city));

        List<City> result = cityService.getAllCities();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("New York");
    }

}

