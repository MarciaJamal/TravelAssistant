package Services;

import Models.WeatherForecast;
import Repository.WeatherForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherForecastService 
{

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    public List<WeatherForecast> getAllForecasts() 
    {
        return weatherForecastRepository.findAll();
    }

    public Optional<WeatherForecast> getForecastById(Long id) 
    {
        return weatherForecastRepository.findById(id);
    }

    public List<WeatherForecast> getForecastsByCityId(Long cityId) 
    {
        return weatherForecastRepository.findByCityId(cityId);
    }

    public WeatherForecast saveForecast(WeatherForecast weatherForecast) 
    {
        return weatherForecastRepository.save(weatherForecast);
    }

    public void deleteForecast(Long id) 
    {
        weatherForecastRepository.deleteById(id);
    }
}