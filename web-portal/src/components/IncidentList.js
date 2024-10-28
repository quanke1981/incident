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
            setIncidents(response.data.content);
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
            <input type="text" placeholder="Search incidents by title..." value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)}/>            
            <ul>
                <li class="header">
                    <div>Title</div>
                    <div>Description</div>
                    <div>Status</div>
                    <div class="action">&nbsp;</div>
                </li>
                {filteredIncidents.length > 0  && filteredIncidents.map((incident) => (
                    <li key={incident.id}>
                        <div>{incident.title}</div>
                        <div>{incident.description}</div>
                        <div>{incident.status}</div>
                        <div class="action">
                            <button onClick={() => editIncident(incident.id)}>Edit</button>
                            <button onClick={() => delIncident(incident.id)}>Delete</button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default IncidentList;