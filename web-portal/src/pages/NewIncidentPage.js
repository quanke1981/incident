import React from 'react';
import IncidentForm from '../components/IncidentForm';
import { createIncident } from '../api';
import { useNavigate } from 'react-router-dom';

function AddIncidentPage() {
    const navigate = useNavigate();
    const handleSubmit = (data) => {
        createIncident(data).then(navigate('/incidents'));
    };

    return (
        <div>
            <h2>add new incident</h2>
            <IncidentForm onSubmit={handleSubmit} />
        </div>
    )
}   


export default AddIncidentPage;