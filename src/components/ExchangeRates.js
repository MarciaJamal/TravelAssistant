import React from 'react';

const ExchangeRates = ({ data }) => {
    if (!data) return null;

    return (
        <div>
            <h2>Exchange Rates</h2>
            <ul>
                {Object.keys(data.rates).map((currency) => (
                    <li key={currency}>
                        {currency}: {data.rates[currency]}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ExchangeRates;
