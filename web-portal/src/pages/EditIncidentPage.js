import React, { useEffect, useState } from 'react';
import { updateIncident, getIncidents } from '../api';
import IncidentForm from '../components/IncidentForm';
import { useParams, useNavigate } from 'react-router-dom';

function EditIncidentPage() {
    const [incident, setIncident] = useState(null);
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        loadIncident();
    }, [id]);

    const loadIncident = () => {
        getIncidents()
        .then(response => {
            const incident = response.data.data.find((inc) => inc.id == id);
            setIncident(incident);
        })
        .catch(error => console.error('Error fetching incidents: ', error));
    };

    const handleSubmit = (data) => {
        updateIncident(id, data).then(navigate('/incidents'));
    };

    return (
        <div>
            <h2>Edit Incident</h2>
            {incident && <IncidentForm onSubmit={handleSubmit} incident={incident} />}
        </div>
    );
}

export default EditIncidentPage;