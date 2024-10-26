import React, { useState } from 'react';



const IncidentForm = ({ onSubmit, incident}) => {
    const [title, setTitle] = useState(incident?.title || '');
    const [description, setDescription] = useState(incident?.description || '');
    const [status, setStatus] = useState(incident?.status || 'Open');

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit({title, description, status});
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Create New Incident</h3>
            <input value={title} onChange={(e) => setTitle(e.target.value)} placeholder='Title' required/>
            <textarea value={description} onChange={(e) => setDescription(e.target.value)} placeholder='Description' required/>
            <select value={status} onChange={(e) => setStatus(e.target.value)}>
                <option value="Open">Open</option>
                <option value="In Progress">In Progress</option>
                <option value="Closed">Closed</option>
            </select>
            <button type="submit">Save</button>
        </form>
    );
};

export default IncidentForm;