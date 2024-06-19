package Controllers;

import Models.ExchangeRate;
import Services.ExchangeRateService;
import ExceptionHandle.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public List<ExchangeRate> getAllRates() {
        return exchangeRateService.getAllRates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRate> getRateById(@PathVariable Long id) {
        ExchangeRate rate = exchangeRateService.getRateById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExchangeRate not found for this id :: " + id));
        return ResponseEntity.ok().body(rate);
    }

    @GetMapping("/pair")
    public ResponseEntity<ExchangeRate> getRateByCurrencyPair(@RequestParam String fromCurrency, @RequestParam String toCurrency) {
        ExchangeRate rate = exchangeRateService.getRateByCurrencyPair(fromCurrency, toCurrency);
        if (rate == null) {
            throw new ResourceNotFoundException("ExchangeRate not found for currency pair :: " + fromCurrency + " to " + toCurrency);
        }
        return ResponseEntity.ok().body(rate);
    }

    @PostMapping
    public ExchangeRate createRate(@RequestBody ExchangeRate exchangeRate) {
        return exchangeRateService.saveRate(exchangeRate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExchangeRate> updateRate(@PathVariable Long id, @RequestBody ExchangeRate rateDetails) {
        ExchangeRate rate = exchangeRateService.getRateById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExchangeRate not found for this id :: " + id));

        rate.setFromCurrency(rateDetails.getFromCurrency());
        rate.setToCurrency(rateDetails.getToCurrency());
        rate.setRate(rateDetails.getRate());
        rate.setTimestamp(rateDetails.getTimestamp());

        final ExchangeRate updatedRate = exchangeRateService.saveRate(rate);
        return ResponseEntity.ok(updatedRate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
        ExchangeRate rate = exchangeRateService.getRateById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExchangeRate not found for this id :: " + id));

        exchangeRateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }
}