package Controllers;

import Models.City;
import Services.CityService;
import ExceptionHandle.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        Optional<City> city = cityService.getCityById(id);
        return city.map(ResponseEntity::ok)
                   .orElseThrow(() -> new ResourceNotFoundException("City not found with id " + id));
    }

    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City cityDetails) {
        Optional<City> city = cityService.getCityById(id);
        if (city.isPresent()) {
            City cityToUpdate = city.get();
            cityToUpdate.setName(cityDetails.getName());
            cityToUpdate.setCountry(cityDetails.getCountry());
            cityToUpdate.setPopulation(cityDetails.getPopulation());
            cityToUpdate.setGdpPerCapita(cityDetails.getGdpPerCapita());
            City updatedCity = cityService.saveCity(cityToUpdate);
            return ResponseEntity.ok(updatedCity);
        } else {
            throw new ResourceNotFoundException("City not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        if (cityService.getCityById(id).isPresent()) {
            cityService.deleteCity(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("City not found with id " + id);
        }
    }
}