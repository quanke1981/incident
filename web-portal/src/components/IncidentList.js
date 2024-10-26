import React, {useEffect, useState} from 'react';
import {getIncidents, deleteIncident} from '../api';
import { useNavigate } from 'react-router-dom';

const IncidentList = () => {
    const [incidents, setIncidents] = useState([]);
    const [searchQuery, setSearchQuery] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        loadIncidents();
    }, []);

    const loadIncidents = () => {
        getIncidents()
        .then(response => {  
            setIncidents(response.data.data);
        })
        .catch(error => console.error('Error fetching incidents: ', error));
    };

    const editIncident = (id) => {
        navigate(`/edit-incident/${id}`);
    }

    const delIncident = (id) => {
        deleteIncident(id).then(() => loadIncidents());        
    }

    const filteredIncidents = incidents.filter((incident) => incident.title.toLowerCase().includes(searchQuery.toLowerCase()));

    return (
        <div>
            <h2>Incident List</h2>
            <input type="text" placeholder="Search Incidents..." value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)}/>
            <ul>
                {filteredIncidents.length > 0  && filteredIncidents.map((incident) => (
                    <li key={incident.id}>
                        {incident.title} - {incident.description} - {incident.status}
                        <button onClick={() => editIncident(incident.id)}>Edit</button>
                        <button onClick={() => delIncident(incident.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default IncidentList;