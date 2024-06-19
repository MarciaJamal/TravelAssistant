import React, { useState } from 'react';
import './App.css';
import Header from './components/Header';
import SearchForm from './components/SearchForm';
import Weather from './components/Weather';
import ExchangeRates from './components/ExchangeRates';
import { getWeather, getExchangeRates } from './services/api';

const App = () => {
    const [weatherData, setWeatherData] = useState(null);
    const [exchangeRatesData, setExchangeRatesData] = useState(null);

    const handleSearch = async (city) => {
        try {
            const weatherResponse = await getWeather(city);
            setWeatherData(weatherResponse.data);
            
            const exchangeRatesResponse = await getExchangeRates();
            setExchangeRatesData(exchangeRatesResponse.data);
        } catch (error) {
            console.error('Error fetching data', error);
        }
    };

    return (
        <div className="App">
            <Header />
            <SearchForm onSearch={handleSearch} />
            <Weather data={weatherData} />
            <ExchangeRates data={exchangeRatesData} />
        </div>
    );
};

export default App;
