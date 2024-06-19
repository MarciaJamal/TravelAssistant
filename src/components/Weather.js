import React from 'react';

const Weather = ({ data }) => {
    if (!data) return null;

    return (
        <div>
            <h2>Weather in {data.name}</h2>
            <p>Temperature: {data.main.temp}°C</p>
            <p>Weather: {data.weather[0].description}</p>
        </div>
    );
};

export default Weather;
