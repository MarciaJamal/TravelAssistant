import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; 

export const getWeather = (city) => {
    return axios.get(`${API_BASE_URL}/weather`, { params: { city } });
};

export const getExchangeRates = () => {
    return axios.get(`${API_BASE_URL}/exchange-rates`);
};
