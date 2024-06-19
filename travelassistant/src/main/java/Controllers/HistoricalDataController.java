package Controllers;

import Models.HistoricalData;
import Services.HistoricalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historical-data")
public class HistoricalDataController {

    @Autowired
    private HistoricalDataService historicalDataService;

    @GetMapping
    public List<HistoricalData> getAllHistoricalData() {
        return historicalDataService.getAllHistoricalData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricalData> getHistoricalDataById(@PathVariable Long id) {
        Optional<HistoricalData> historicalData = historicalDataService.getHistoricalDataById(id);
        return historicalData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/city/{cityId}")
    public List<HistoricalData> getHistoricalDataByCityId(@PathVariable Long cityId) {
        return historicalDataService.getHistoricalDataByCityId(cityId);
    }

    @PostMapping
    public HistoricalData createHistoricalData(@RequestBody HistoricalData historicalData) {
        return historicalDataService.saveHistoricalData(historicalData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricalData> updateHistoricalData(@PathVariable Long id, @RequestBody HistoricalData historicalDataDetails) {
        Optional<HistoricalData> historicalData = historicalDataService.getHistoricalDataById(id);
        if (historicalData.isPresent()) {
            HistoricalData historicalDataToUpdate = historicalData.get();
            historicalDataToUpdate.setCity(historicalDataDetails.getCity());
            historicalDataToUpdate.setYear(historicalDataDetails.getYear());
            historicalDataToUpdate.setPopulation(historicalDataDetails.getPopulation());
            historicalDataToUpdate.setGdp(historicalDataDetails.getGdp());
            HistoricalData updatedHistoricalData = historicalDataService.saveHistoricalData(historicalDataToUpdate);
            return ResponseEntity.ok(updatedHistoricalData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoricalData(@PathVariable Long id) {
        historicalDataService.deleteHistoricalData(id);
        return ResponseEntity.noContent().build();
    }
}