package Controllers;

import Models.WeatherForecast;
import Services.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weather")
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping
    public List<WeatherForecast> getAllForecasts() {
        return weatherForecastService.getAllForecasts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherForecast> getForecastById(@PathVariable Long id) {
        Optional<WeatherForecast> forecast = weatherForecastService.getForecastById(id);
        return forecast.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/city/{cityId}")
    public List<WeatherForecast> getForecastsByCityId(@PathVariable Long cityId) {
        return weatherForecastService.getForecastsByCityId(cityId);
    }

    @PostMapping
    public WeatherForecast createForecast(@RequestBody WeatherForecast weatherForecast) {
        return weatherForecastService.saveForecast(weatherForecast);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeatherForecast> updateForecast(@PathVariable Long id, @RequestBody WeatherForecast forecastDetails) {
        Optional<WeatherForecast> forecast = weatherForecastService.getForecastById(id);
        if (forecast.isPresent()) {
            WeatherForecast forecastToUpdate = forecast.get();
            forecastToUpdate.setCity(forecastDetails.getCity());
            forecastToUpdate.setTimestamp(forecastDetails.getTimestamp());
            forecastToUpdate.setDescription(forecastDetails.getDescription());
            forecastToUpdate.setTemperature(forecastDetails.getTemperature());
            forecastToUpdate.setHumidity(forecastDetails.getHumidity());
            WeatherForecast updatedForecast = weatherForecastService.saveForecast(forecastToUpdate);
            return ResponseEntity.ok(updatedForecast);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForecast(@PathVariable Long id) {
        weatherForecastService.deleteForecast(id);
        return ResponseEntity.noContent().build();
    }
}
