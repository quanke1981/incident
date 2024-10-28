import React, { useState } from 'react';



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
                <option value="OPEN">OPEN</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="CLOSED">Closed</option>
            </select>
            <button type="submit" className="small-button">Save</button>
        </form>
    );
};

export default IncidentForm;