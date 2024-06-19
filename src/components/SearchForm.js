import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

const SearchForm = ({ onSearch }) => {
    const [city, setCity] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        onSearch(city);
    };

    return (
        <form onSubmit={handleSubmit} className="d-flex justify-content-center">
            <input 
                type="text" 
                value={city} 
                onChange={(e) => setCity(e.target.value)} 
                placeholder="Enter city" 
                className="form-control mr-2"
            />
            <button type="submit" className="btn btn-primary">
                <FontAwesomeIcon icon={faSearch} /> Search
            </button>
        </form>
    );
};

export default SearchForm;
