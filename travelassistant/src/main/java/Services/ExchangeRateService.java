package Services;

import Models.ExchangeRate;
import Repository.ExchangeRateRepository;
import ExceptionHandle.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public List<ExchangeRate> getAllRates() {
        return exchangeRateRepository.findAll();
    }

    public Optional<ExchangeRate> getRateById(Long id) {
        return exchangeRateRepository.findById(id);
    }

    public ExchangeRate getRateByCurrencyPair(String fromCurrency, String toCurrency) {
        return exchangeRateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency)
                .orElseThrow(() -> new ResourceNotFoundException("ExchangeRate not found for currency pair: " + fromCurrency + " to " + toCurrency));
    }

    public ExchangeRate saveRate(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    public void deleteRate(Long id) {
        exchangeRateRepository.deleteById(id);
    }
}