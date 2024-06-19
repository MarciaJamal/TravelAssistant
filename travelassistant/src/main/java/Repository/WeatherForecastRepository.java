package Repository;

import Models.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {
    List<WeatherForecast> findByCityId(Long cityId);
}