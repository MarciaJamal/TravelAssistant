package Services;

import Models.HistoricalData;
import Repository.HistoricalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricalDataService {

    @Autowired
    private HistoricalDataRepository historicalDataRepository;

    public List<HistoricalData> getAllHistoricalData() {
        return historicalDataRepository.findAll();
    }

    public Optional<HistoricalData> getHistoricalDataById(Long id) {
        return historicalDataRepository.findById(id);
    }

    public List<HistoricalData> getHistoricalDataByCityId(Long cityId) {
        return historicalDataRepository.findByCityId(cityId);
    }

    public HistoricalData saveHistoricalData(HistoricalData historicalData) {
        return historicalDataRepository.save(historicalData);
    }

    public void deleteHistoricalData(Long id) {
        historicalDataRepository.deleteById(id);
    }
}
