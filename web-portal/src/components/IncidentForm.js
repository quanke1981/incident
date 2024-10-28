import React, { useState } from 'react';
import {STATUS_VALUES} from '../const';


const IncidentForm = ({ onSubmit, incident}) => {
    const [title, setTitle] = useState(incident?.title || '');
    const [description, setDescription] = useState(incident?.description || '');
    const [status, setStatus] = useState(incident?.status || 'OPEN');

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit({title, description, status});
    };

    return (
        <form onSubmit={handleSubmit}>
            <input value={title} onChange={(e) => setTitle(e.target.value)} placeholder='Title' required/>
            <textarea value={description} onChange={(e) => setDescription(e.target.value)} placeholder='Description' required/>    
            <select value={status} onChange={(e) => setStatus(e.target.value)}>
                {Object.entries(STATUS_VALUES).map(([key, text]) => (
                    <option key={key}>{text}</option>
                ))}
            </select>
            <button type="submit" className="small-button">Save</button>
        </form>
    );
};

export default IncidentForm;